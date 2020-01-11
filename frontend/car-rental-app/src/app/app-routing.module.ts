import { HomeComponent } from './features/home/home.component';
import { RentalOfficeComponent } from './features/rental-office/rental-office.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'rental-office',
    component: RentalOfficeComponent
  },

  {
    path: '',
    component: HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
