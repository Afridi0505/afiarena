import { Routes } from '@angular/router';
import { ArenaShellComponent } from './arena-shell.component';
import { authGuard } from './core/guards/auth.guard';
import { AuthPageComponent } from './features/auth/auth-page.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: AuthPageComponent, data: { mode: 'login' } },
  { path: 'register', component: AuthPageComponent, data: { mode: 'register' } },
  { path: ':page', component: ArenaShellComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: 'login' },
];
