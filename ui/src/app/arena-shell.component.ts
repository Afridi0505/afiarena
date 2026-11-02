import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ArenaApiService, Game } from './arena-api.service';
import { AuthService } from './core/services/auth.service';

@Component({selector:'app-arena-shell',standalone:true,imports:[RouterLink,FormsModule],templateUrl:'./arena-shell.component.html',styleUrl:'./arena-shell.component.scss'})
export class ArenaShellComponent implements OnInit {
  route=inject(ActivatedRoute); router=inject(Router); api=inject(ArenaApiService); auth=inject(AuthService);
  page=signal('login');
  games=signal<Game[]>([]); loadingGames=signal(false); authBusy=signal(false); message=signal(''); messageIsError=signal(false);
  credentials={gameUserName:'',gameUserPassword:''}; registerCredentials={gameUserName:'',gameUserPassword:''}; editingGame=signal<Game|null>(null); editImage?:File;
  userMenuOpen=signal(false);
  userName=(typeof localStorage === 'undefined' ? null : localStorage.getItem('afiarena-user'))||'Player';
  nav=[['dashboard','◈','Dashboard'],['tournaments','♜','Tournaments'],['matches','◉','My Matches'],['leaderboard','♛','Leaderboard'],['profile','◌','Profile']];
  tournaments=[{game:'BGMI',name:'Battle Royale Championship',date:'Apr 10 – Apr 20',pool:'₹1,00,000',c:'bgmi'},{game:'VALORANT',name:'Valorant Showdown',date:'Apr 15 – Apr 30',pool:'₹50,000',c:'valorant'},{game:'FC 24',name:'Pro League Cup',date:'Apr 12 – Apr 25',pool:'₹30,000',c:'fc'}];
  ngOnInit(){this.route.paramMap.subscribe(params=>{const currentPage=params.get('page')||'login';this.page.set(currentPage);if((currentPage==='dashboard'||currentPage==='tournaments')&&typeof localStorage!=='undefined'&&localStorage.getItem('afiarena-token'))this.loadGames()})}
  go(p:string){this.router.navigate([`/${p}`])}
  loadGames(){this.loadingGames.set(true);this.api.getGames().subscribe({next:games=>{this.games.set(games);this.loadingGames.set(false)},error:()=>{this.loadingGames.set(false);this.showMessage('Could not load games. Start the Spring Boot backend on port 8080.',true)}})}
  login(){if(!this.credentials.gameUserName||!this.credentials.gameUserPassword){this.showMessage('Enter your gamer tag and password.',true);return}this.authBusy.set(true);this.auth.login(this.credentials).subscribe({next:()=>{this.userName=this.credentials.gameUserName;this.authBusy.set(false);this.go('dashboard')},error:()=>{this.authBusy.set(false);this.showMessage('Login failed. Use a valid DB user with a BCrypt password.',true)}})}
  register(){if(!this.registerCredentials.gameUserName||!this.registerCredentials.gameUserPassword){this.showMessage('Enter a gamer tag and password.',true);return}this.authBusy.set(true);this.auth.register(this.registerCredentials).subscribe({next:()=>{this.authBusy.set(false);this.credentials={...this.registerCredentials};this.showMessage('Account created. Sign in with your new account.');this.go('login')},error:()=>{this.authBusy.set(false);this.showMessage('Could not create the account. That gamer tag may already exist.',true)}})}
  toggleUserMenu(){this.userMenuOpen.update(open=>!open)}
  logout(){this.userMenuOpen.set(false);this.auth.logout().subscribe({next:()=>{this.userName='Player';this.go('login')},error:()=>this.showMessage('Logout failed. You are still signed in.',true)})}
  startEdit(game:Game){this.editingGame.set({...game});this.message.set('')}
  chooseImage(event:Event){this.editImage=(event.target as HTMLInputElement).files?.[0]}
  saveGame(){const game=this.editingGame();if(!game)return;this.api.updateGame(game,this.editImage).subscribe({next:updated=>{this.games.update(items=>items.map(item=>item.gameNumber===updated.gameNumber?updated:item));this.editingGame.set(null);this.showMessage('Game updated successfully.')},error:()=>this.showMessage('Update could not be completed. The current backend requires an image file for every update.',true)})}
  gameClass(game:Game){const value=`${game.gameName} ${game.gameType}`.toLowerCase();return value.includes('valorant')?'valorant':value.includes('fc')||value.includes('football')?'fc':'bgmi'}
  private showMessage(text:string,isError=false){this.message.set(text);this.messageIsError.set(isError)}
}
