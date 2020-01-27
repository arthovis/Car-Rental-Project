import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RentalsComponent } from './rentals.component';
import { RentalsOverviewComponent } from './rentals-overview/rentals-overview.component';
import { RentalsDetailsComponent } from './rentals-details/rentals-details.component';
import { EmployeesListComponent } from '../employees/employees-list/employees-list.component';
import { RentalsUpdateComponent } from './rentals-update/rentals-update.component';


const routes: Routes = [
  {
    path: 'rentals',
    component: RentalsComponent,
    children: [
      {
        path: '',
        component: RentalsOverviewComponent
      },
    ]
  },
  {
    path: 'rentals/:id/details',
    component: RentalsDetailsComponent
  },
  {
    path: 'rentals/:id/employees-list',
    component: EmployeesListComponent
  },
  {
    path: 'rentals/:id/update',
    component: RentalsUpdateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RentalsRoutingModule { }
