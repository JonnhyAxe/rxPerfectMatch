import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SampleRoutingModule } from './sample-routing.module';
import { SampleListComponent } from './sample-list/sample-list.component';
import { SampleCreateComponent } from './sample-create/sample-create.component';

@NgModule({
  imports: [
    CommonModule,
    SampleRoutingModule
  ],
  declarations: [SampleListComponent, SampleCreateComponent]
})
export class SampleModule { }
