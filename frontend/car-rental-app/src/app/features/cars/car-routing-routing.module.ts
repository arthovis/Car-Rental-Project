import { CarDetailsComponent } from './car-details/car-details.component';
import { CarUpdateComponent } from './car-update/car-update.component';
import { CarNewComponent } from './car-new/car-new.component';
import { CarOverviewComponent } from './car-overview/car-overview.component';
import { CarsComponent } from './cars.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'cars',
    component: CarsComponent,
    children: [
      {
      path: '',
      component: CarOverviewComponent
      },
    ]
  },
  {
    path: 'cars/new',
    component: CarNewComponent
  },
  {
    path: 'cars/:id/update',
    component: CarUpdateComponent
  },
  {
    path: 'cars/:id/details',
    component: CarDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarRoutingRoutingModule { }
