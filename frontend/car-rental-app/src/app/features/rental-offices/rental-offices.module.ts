import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { RentalOfficesRoutingModule } from './rental-offices-routing.module';
import { RentalOfficesComponent } from './rental-offices.component';
import { RentalOfficeNewComponent } from './rental-office-new/rental-office-new.component';
import { RentalOfficeOverviewComponent } from './rental-office-overview/rental-office-overview.component';
import { RentalOfficeDetailsComponent } from './rental-office-details/rental-office-details.component';
import { BranchesListModule } from '../branches/branches-list/branches-list.module';
import { RentalOfficeUpdateComponent } from './rental-office-update/rental-office-update.component';


@NgModule({
  declarations: [
    RentalOfficesComponent,
    RentalOfficeOverviewComponent,
    RentalOfficeNewComponent,
    RentalOfficeDetailsComponent,
    RentalOfficeUpdateComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    FontAwesomeModule,
    BranchesListModule,
    RentalOfficesRoutingModule
  ]
})
export class RentalOfficesModule { }
