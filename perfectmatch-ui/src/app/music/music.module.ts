import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MusicRoutingModule } from './music-routing.module';
import { MusicListComponent } from './music-list/music-list.component';
import { MusicCreateComponent } from './music-create/music-create.component';



@NgModule({
  imports: [
    CommonModule,
    MusicRoutingModule
  ],
  declarations: [MusicListComponent, MusicCreateComponent]
})
export class MusicModule { }
