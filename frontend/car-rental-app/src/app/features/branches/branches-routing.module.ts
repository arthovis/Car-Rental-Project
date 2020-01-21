import { CarsListComponent } from './../cars/cars-list/cars-list.component';
import { EmployeesListComponent } from './../employees/employees-list/employees-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BranchOverviewComponent } from './branch-overview/branch-overview.component';
import { BranchesComponent } from './branches.component';
import { BranchNewComponent } from './branch-new/branch-new.component';
import { BranchUpdateComponent } from './branch-update/branch-update.component';
import { BranchDetailsComponent } from './branch-details/branch-details.component';


const routes: Routes = [
  {
    path: 'branches',
    component: BranchesComponent,
    children: [
      {
        path: '',
        component: BranchOverviewComponent
      },
    ]
  },
  {
    path: 'branches/new',
    component: BranchNewComponent
  },
  {
    path: 'branches/:id/update',
    component: BranchUpdateComponent
  },
  {
    path: 'branches/:id/details',
    component: BranchDetailsComponent
  },
  {
    path: 'branches/:id/employees-list',
    component: EmployeesListComponent
  },
  {
    path: 'branches/:id/cars-list',
    component: CarsListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchesRoutingModule { }
