import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MusicListComponent } from './music-list/music-list.component'

const routes: Routes = [
{path: 'music', component: MusicListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MusicRoutingModule { }
