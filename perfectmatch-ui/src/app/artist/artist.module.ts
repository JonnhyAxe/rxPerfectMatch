import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ArtistRoutingModule } from './artist-routing.module';
import { ArtistListComponent } from './artist-list/artist-list.component';
import { ArtistCreateComponent } from './artist-create/artist-create.component';

@NgModule({
  imports: [
    CommonModule,
    ArtistRoutingModule
  ],
  declarations: [ArtistListComponent, ArtistCreateComponent]
})
export class ArtistModule { }
