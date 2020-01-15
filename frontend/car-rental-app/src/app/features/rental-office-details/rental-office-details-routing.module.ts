import { RentalOfficeDetailsComponent } from './rental-office-details.component';
import { BranchesListComponent } from './../branches-list/branches-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'rental-offices/:id/branches-list',
    component: BranchesListComponent
  },
  {
    path: 'rental-offices/:id/rental-office-detail',
    component: RentalOfficeDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RentalOfficeDetailsRoutingModule { }
