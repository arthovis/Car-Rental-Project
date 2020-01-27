import { RentalsUpdateComponent } from './rentals-update/rentals-update.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RentalsRoutingModule } from './rentals-routing.module';
import { RentalsOverviewComponent } from './rentals-overview/rentals-overview.component';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { EmployeesListModule } from '../employees/employees-list/employees-list.module';
import { RentalsComponent } from './rentals.component';
import { RentalsDetailsComponent } from './rentals-details/rentals-details.component';


@NgModule({
  declarations: [
    RentalsComponent,
    RentalsOverviewComponent,
    RentalsDetailsComponent,
    RentalsUpdateComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    FontAwesomeModule,
    EmployeesListModule,
    RentalsRoutingModule
  ]
})
export class RentalsModule { }
