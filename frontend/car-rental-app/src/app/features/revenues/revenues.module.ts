import { RevenuesComponent } from './revenues.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RevenuesRoutingModule } from './revenues-routing.module';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RentalBranchesListComponent } from './rental-branches-list/rental-branches-list.component';


@NgModule({
  declarations: [
    RevenuesComponent,
    RentalBranchesListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    FontAwesomeModule,
    RevenuesRoutingModule
  ]
})
export class RevenuesModule { }
