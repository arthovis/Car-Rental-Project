import { EmployeesComponent } from './employees.component';
import { EmployeesUpdateComponent } from './employees-update/employees-update.component';
import { EmployeesDetailsComponent } from './employees-details/employees-details.component';
import { EmployeesOverviewComponent } from './employees-overview/employees-overview.component';
import { EmployeesNewComponent } from './employees-new/employees-new.component';
import { EmployeesRoutingRoutingModule } from './employees-routing/employees-routing-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


@NgModule({
    declarations: [
        EmployeesComponent,
        EmployeesNewComponent,
        EmployeesOverviewComponent,
        EmployeesDetailsComponent,
        EmployeesUpdateComponent,
    ],
    imports: [
        CommonModule,
        FormsModule,
        MaterialModule,
        FontAwesomeModule,
        EmployeesRoutingRoutingModule
    ],
})

export class EmployeeModule { }
