import { Routes } from '@angular/router';
import { ArenaShellComponent } from './arena-shell.component';

export const routes: Routes = [{ path: '', component: ArenaShellComponent, pathMatch: 'full' }, { path: ':page', component: ArenaShellComponent }];
