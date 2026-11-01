import { Component, computed, inject } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({selector:'app-arena-shell',standalone:true,imports:[RouterLink],templateUrl:'./arena-shell.component.html',styleUrl:'./arena-shell.component.scss'})
export class ArenaShellComponent {
  route=inject(ActivatedRoute); router=inject(Router);
  page=computed(()=>this.route.snapshot.paramMap.get('page')||'landing');
  nav=[['dashboard','◈','Dashboard'],['tournaments','♜','Tournaments'],['matches','◉','My Matches'],['leaderboard','♛','Leaderboard'],['profile','◌','Profile']];
  tournaments=[{game:'BGMI',name:'Battle Royale Championship',date:'Apr 10 – Apr 20',pool:'₹1,00,000',c:'bgmi'},{game:'VALORANT',name:'Valorant Showdown',date:'Apr 15 – Apr 30',pool:'₹50,000',c:'valorant'},{game:'FC 24',name:'Pro League Cup',date:'Apr 12 – Apr 25',pool:'₹30,000',c:'fc'}];
  go(p:string){this.router.navigate([p==='landing'?'/':'/'+p])} login(){this.go('dashboard')}
}
