import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatchRoutingModule } from './match-routing.module';
import { MatchListComponent } from './match-list/match-list.component';

@NgModule({
  imports: [
    CommonModule,
    MatchRoutingModule
  ],
  declarations: [MatchListComponent]
})
export class MatchModule { }
