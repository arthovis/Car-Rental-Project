import { CarsListModule } from './../cars/cars-list/cars-list.module';
import { EmployeesListModule } from './../employees/employees-list/employees-list.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { BranchesRoutingModule } from './branches-routing.module';
import { BranchesComponent } from './branches.component';
import { BranchNewComponent } from './branch-new/branch-new.component';
import { BranchOverviewComponent } from './branch-overview/branch-overview.component';
import { BranchUpdateComponent } from './branch-update/branch-update.component';
import { BranchDetailsComponent } from './branch-details/branch-details.component';

@NgModule({
  declarations: [
    BranchesComponent,
    BranchNewComponent,
    BranchOverviewComponent,
    BranchUpdateComponent,
    BranchDetailsComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    FontAwesomeModule,
    EmployeesListModule,
    CarsListModule,
    BranchesRoutingModule
  ],
})
export class BranchesModule { }
