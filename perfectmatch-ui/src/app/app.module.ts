import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule }    from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ArtistModule } from './artist/artist.module';
import { MusicModule } from './music/music.module'
import { SampleModule } from './sample/sample.module';
import { MatchModule } from './match/match.module';

import { ArtistService } from './artist.service';
import { SampleService } from './sample/sample.service';
import { MatchService } from './match/match.service';
import { MusicService } from './music/music.service'

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
        
    ArtistModule,
    MusicModule,
    SampleModule,
    MatchModule
  ],
  providers: [ArtistService, SampleService, MatchService, MusicService],
  bootstrap: [AppComponent]
})
export class AppModule { }
