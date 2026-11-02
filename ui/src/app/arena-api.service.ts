import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export interface Game { gameNumber: number; gameName: string; gameType: string; imageName?: string; imageType?: string; }
export interface Credentials { gameUserName: string; gameUserPassword: string; }

@Injectable({ providedIn: 'root' })
export class ArenaApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api';
  login(credentials: Credentials) { return this.http.post(`${this.baseUrl}/login`, credentials, { responseType: 'text' }); }
  register(credentials: Credentials) { return this.http.post(`${this.baseUrl}/games/register-user`, { id: Date.now(), ...credentials }); }
  getGames() { return this.http.get<Game[]>(`${this.baseUrl}/games/getAllGames`, { headers: this.authHeaders() }); }
  updateGame(game: Game, image?: File) {
    const data = new FormData();
    data.append('gameCollections', new Blob([JSON.stringify(game)], { type: 'application/json' }));
    if (image) data.append('multipartFile', image);
    return this.http.put<Game>(`${this.baseUrl}/games/updateGames/${game.gameNumber}`, data, { headers: this.authHeaders() });
  }
  private authHeaders() { const token = typeof localStorage === 'undefined' ? null : localStorage.getItem('afiarena-token'); return token ? new HttpHeaders({ Authorization: `Bearer ${token}` }) : new HttpHeaders(); }
}
