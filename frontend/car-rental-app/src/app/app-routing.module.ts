import { PageNotFoundComponent } from './features/page-not-found/page-not-found.component';
import { HomeComponent } from './features/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'bookings',
    loadChildren: () => import('./features/bookings/bookings.module').then(mod => mod.BookingsModule)
  },
  {
    path: 'rental-offices',
    loadChildren: () => import('./features/rental-offices/rental-offices.module').then(mod => mod.RentalOfficesModule)
  },
  {
    path: 'branches',
    loadChildren: () => import('./features/branches/branches.module').then(mod => mod.BranchesModule)
  },
  {
    path: 'rentals',
    loadChildren: () => import('./features/rentals/rentals.module').then(mod => mod.RentalsModule)
  },
  {
    path: 'returns',
    loadChildren: () => import('./features/returns/returns.module').then(mod => mod.ReturnsModule)
  },
  {
    path: 'cars',
    loadChildren: () => import('./features/cars/cars.module').then(mod => mod.CarsModule)
  },
  {
    path: 'employees',
    loadChildren: () => import('./features/employees/employee.module').then(mod => mod.EmployeeModule)
  },
  {
    path: 'revenues',
    loadChildren: () => import('./features/revenues/revenues.module').then(mod => mod.RevenuesModule)
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
