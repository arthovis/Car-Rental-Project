import { FormsModule } from '@angular/forms';
import { BranchesListComponent } from './../branches-list/branches-list.component';
import { RentalOfficeDetailsComponent } from './rental-office-details.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RentalOfficeDetailsRoutingModule } from './rental-office-details-routing.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RentalOfficeDetailsRoutingModule
  ]
})
export class RentalOfficeDetailsModule { }
