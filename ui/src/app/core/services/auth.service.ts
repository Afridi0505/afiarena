import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs';

export interface LoginPayload { gameUserName: string; gameUserPassword: string; }

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api';

  login(payload: LoginPayload) {
    return this.http.post(`${this.baseUrl}/login`, payload, { responseType: 'text' }).pipe(
      map(token => {
        if (!this.isValidJwt(token)) throw new Error('The server did not return a valid login token.');
        localStorage.setItem('afiarena-token', token);
        localStorage.setItem('afiarena-user', payload.gameUserName);
        return token;
      })
    );
  }

  register(payload: LoginPayload) {
    return this.http.post(`${this.baseUrl}/games/register-user`, {
      id: Math.floor(Math.random() * 2_000_000_000),
      ...payload,
    });
  }

  logout() {
    return this.http.post(`${this.baseUrl}/logout`, {}, { headers: this.authHeaders() }).pipe(map(() => this.clearSession()));
  }

  isAuthenticated() {
    return typeof localStorage !== 'undefined' && this.isValidJwt(localStorage.getItem('afiarena-token') ?? '');
  }

  clearSession() {
    localStorage.removeItem('afiarena-token');
    localStorage.removeItem('afiarena-user');
  }

  private isValidJwt(token: string) {
    try {
      const [, payload] = token.split('.');
      const decoded = JSON.parse(atob(payload.replace(/-/g, '+').replace(/_/g, '/')));
      return token.split('.').length === 3 && typeof decoded.exp === 'number' && decoded.exp * 1000 > Date.now();
    } catch { return false; }
  }

  private authHeaders() {
    const token = typeof localStorage === 'undefined' ? null : localStorage.getItem('afiarena-token');
    return token ? new HttpHeaders({ Authorization: `Bearer ${token}` }) : new HttpHeaders();
  }
}
