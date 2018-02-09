import { Component, OnInit } from '@angular/core';

import {MatchService} from '../match.service'
import {Match} from '../match'


@Component({
  selector: 'app-match-list',
  templateUrl: './match-list.component.html',
  styleUrls: ['./match-list.component.css']
})
export class MatchListComponent implements OnInit {
  
  matchs : Match[];
  
  constructor(private matchService: MatchService) { }

  ngOnInit() {
    this.getMatchs();
  }

  getMatchs(): void {
     this.matchService.getMatchs()
    .subscribe(matchs => this.matchs = matchs);
  }

}
