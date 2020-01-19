import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RentalOfficesComponent } from './rental-offices.component';
import { RentalOfficeOverviewComponent } from './rental-office-overview/rental-office-overview.component';
import { RentalOfficeNewComponent } from './rental-office-new/rental-office-new.component';
import { RentalOfficeDetailsComponent } from './rental-office-details/rental-office-details.component';
import { BranchesListComponent } from '../branches/branches-list/branches-list.component';
import { RentalOfficeUpdateComponent } from './rental-office-update/rental-office-update.component';

const routes: Routes = [
  {
    path: 'rental-offices',
    component: RentalOfficesComponent,
    children: [
      {
        path: '',
        component: RentalOfficeOverviewComponent
      },
    ]
  },
  {
    path: 'rental-offices/new',
    component: RentalOfficeNewComponent
  },
  {
    path: 'rental-offices/:id/update',
    component: RentalOfficeUpdateComponent
  },
  {
    path: 'rental-offices/:id/details',
    component: RentalOfficeDetailsComponent,
  },
  {
    path: 'rental-offices/:id/branches-list',
    component: BranchesListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RentalOfficesRoutingModule { }
