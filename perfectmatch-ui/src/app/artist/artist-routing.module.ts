import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ArtistListComponent } from './artist-list/artist-list.component';
import { ArtistCreateComponent } from './artist-create/artist-create.component';
 
 
const routes: Routes = [
  {path: 'artist', component: ArtistListComponent},
  {path: 'artist/create', component: ArtistCreateComponent},
  {path: 'artist/edit/:id', component: ArtistCreateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArtistRoutingModule { }
