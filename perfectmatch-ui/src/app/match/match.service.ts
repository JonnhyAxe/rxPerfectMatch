import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import  { Match } from './match'
import  { MATCHS } from './matches-mock' 
 
@Injectable()
export class MatchService {

  constructor() { }
  
  getMatchs(): Observable<Match[]> {
    return of(MATCHS);
  }
  
}
