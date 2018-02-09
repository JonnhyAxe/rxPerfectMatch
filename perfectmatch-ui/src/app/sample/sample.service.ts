import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { SAMPLES } from './samples-mock'
import { Sample } from './sample'


@Injectable()
export class SampleService {

  constructor() { }

  getSamples(): Observable<Sample[]> {
    return of(SAMPLES);
  }
}
