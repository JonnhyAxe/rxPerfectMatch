import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';


import {MusicService} from '../music.service'
import {Music} from '../music'

//import {MUSICS} from '../musics-mock'


@Component({
  selector: 'app-music-list',
  templateUrl: './music-list.component.html',
  styleUrls: ['./music-list.component.css']
})
export class MusicListComponent implements OnInit {

  //musics = MUSICS;
  musics : Music[];
  
  constructor(private musicService: MusicService) { }

  ngOnInit() {
      this.getMusics()
  }
  
   getMusics(): void {
     this.musicService.getMusics()
    .subscribe(musics => this.musics = musics);
  }

}
