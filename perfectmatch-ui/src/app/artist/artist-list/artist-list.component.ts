import { Component, OnInit } from '@angular/core';

import { ARTISTS } from '../artists-mock';

@Component({
  selector: 'app-artist-list',
  templateUrl: './artist-list.component.html',
  styleUrls: ['./artist-list.component.css']
})

export class ArtistListComponent implements OnInit {

  artists = ARTISTS;
  
  constructor() { }

  ngOnInit() {
  }

}
