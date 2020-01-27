import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { EmployeesListModule } from '../employees/employees-list/employees-list.module';
import { ReturnsOverviewComponent } from './returns-overview/returns-overview.component';
import { ReturnsComponent } from './returns.component';
import { ReturnsDetailsComponent } from './returns-details/returns-details.component';
import { ReturnsUpdateComponent } from './returns-update/returns-update.component';
import { ReturnsRoutingModule } from './returns-routing.module';

@NgModule({
  declarations: [
    ReturnsComponent,
    ReturnsOverviewComponent,
    ReturnsDetailsComponent,
    ReturnsUpdateComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    FontAwesomeModule,
    EmployeesListModule,
    ReturnsRoutingModule
  ]
})
export class ReturnsModule { }
