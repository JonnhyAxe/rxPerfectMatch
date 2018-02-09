import { Component, OnInit } from '@angular/core';

import {Sample} from '../sample'
import {SampleService} from '../sample.service'


@Component({
  selector: 'app-sample-list',
  templateUrl: './sample-list.component.html',
  styleUrls: ['./sample-list.component.css']
})
export class SampleListComponent implements OnInit {

  samples: Sample[];
  
  constructor(private sampleService: SampleService) { }

  ngOnInit() {
    this.getSamples();
  }

  getSamples(): void {
     this.sampleService.getSamples()
    .subscribe(samples => this.samples = samples);
  }
  
}
