import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';


import {MUSICS} from './musics-mock'
import {Music} from './music'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable()
export class MusicService {

   private musicUrl = 'http://localhost:8443/music/repo';  // URL to web api

  
  constructor(private http: HttpClient) { }

  getMockedMusics(): Observable<Music[]> {
    return of(MUSICS);
  }
  
  getMusics(): Observable<Music[]> {
    return this.http.get<Music[]>(this.musicUrl)
      .pipe(
        tap(musics => this.log(`fetched music`)),
        catchError(this.handleError('getMusics', []))
      );
  }
  
  
  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
    /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log('MusicService: ' + message);
  }
  
}
