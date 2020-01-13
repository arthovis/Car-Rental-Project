import { BranchOverviewComponent } from './features/branch-overview/branch-overview.component';
import { PageNotFoundComponent } from './features/page-not-found/page-not-found.component';
import { RentalOfficeDetailsComponent } from './features/rental-office-details/rental-office-details.component';
import { BranchComponent } from './features/branch/branch.component';
import { RentalOfficeOverviewComponent } from './features/rental-office-overview/rental-office-overview.component';
import { HomeComponent } from './features/home/home.component';
import { RentalOfficeComponent } from './features/rental-office/rental-office.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'rental-offices',
    component: RentalOfficeOverviewComponent
  },
  {
    path: 'rental-offices/new',
    component: RentalOfficeComponent
  },
  {
    path: 'rental-offices/:id/details',
    component: RentalOfficeDetailsComponent
  },
  {
    path: 'branches',
    component: BranchOverviewComponent
  },
  {
    path: 'branches/new',
    component: BranchComponent
  },
  {
    path: '',
    component: HomeComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
