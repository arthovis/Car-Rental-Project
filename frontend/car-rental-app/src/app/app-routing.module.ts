import { RentalOfficeDetailsComponent } from './features/rental-office-details/rental-office-details.component';
import { BranchComponent } from './features/branch/branch.component';
import { RentalOfficeOverviewComponent } from './features/rental-office-overview/rental-office-overview.component';
import { HomeComponent } from './features/home/home.component';
import { RentalOfficeComponent } from './features/rental-office/rental-office.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { componentFactoryName } from '@angular/compiler';


const routes: Routes = [
  {
    path: 'rental-offices/new',
    component: RentalOfficeComponent
  },

  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'rental-offices',
    component: RentalOfficeOverviewComponent
  },
  {
    path: 'rental-offices/:id/details',
    component: RentalOfficeDetailsComponent
  },
  {
    path: 'branch',
    component: BranchComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
