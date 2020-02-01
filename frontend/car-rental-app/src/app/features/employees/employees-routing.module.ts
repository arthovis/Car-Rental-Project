
import { EmployeesComponent } from './employees.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeesOverviewComponent } from './employees-overview/employees-overview.component';
import { EmployeesNewComponent } from './employees-new/employees-new.component';
import { EmployeesUpdateComponent } from './employees-update/employees-update.component';
import { EmployeesDetailsComponent } from './employees-details/employees-details.component';


const routes: Routes = [
  {
    path: 'employees',
    component: EmployeesComponent,
    children: [
      {
        path: '',
        component: EmployeesOverviewComponent
      },
    ]
  },
  {
    path: 'employees/new',
    component: EmployeesNewComponent
  },
  {
    path: 'employees/:id/update',
    component: EmployeesUpdateComponent
  },
  {
    path: 'employees/:id/details',
    component: EmployeesDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeesRoutingModule { }
