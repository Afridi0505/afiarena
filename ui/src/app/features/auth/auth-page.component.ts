import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-auth-page', standalone: true, imports: [FormsModule, RouterLink],
  templateUrl: './auth-page.component.html', styleUrl: './auth-page.component.scss'
})
export class AuthPageComponent {
  private readonly route = inject(ActivatedRoute); private readonly router = inject(Router); private readonly auth = inject(AuthService);
  readonly mode = this.route.snapshot.data['mode'] as 'login' | 'register';
  credentials = { gameUserName: '', gameUserPassword: '' }; busy = false; error = '';
  submit() {
    if (!this.credentials.gameUserName || !this.credentials.gameUserPassword) { this.error = 'Enter your gamer tag and password.'; return; }
    this.busy = true; this.error = '';
    const request = this.mode === 'login' ? this.auth.login(this.credentials) : this.auth.register(this.credentials);
    request.subscribe({ next: () => { this.busy = false; this.router.navigate([this.mode === 'login' ? '/dashboard' : '/login']); }, error: () => { this.busy = false; this.error = this.mode === 'login' ? 'Login failed. Check your DB user and BCrypt password.' : 'Registration failed. Check the backend registration permission and gamer tag.'; } });
  }
}
