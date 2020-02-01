import { CustomerOverviewComponent } from './../customer-overview/customer-overview.component';
import { CustomerModule } from './../customer.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerUpdateComponent } from '../customer-update/customer-update.component';
import { CustomerDetailsComponent } from '../customer-details/customer-details.component';


const routes: Routes = [
  {
    path: 'customers',
    component: CustomerModule,
    children: [
      {
      path: '',
      component: CustomerOverviewComponent
      },
    ]
  },
  {
    path: 'customers/new',
    component: CustomerOverviewComponent
  },
  {
    path: 'customers/:id/update',
    component: CustomerUpdateComponent
  },
  {
    path: 'customers/:id/details',
    component: CustomerDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingRoutingModule { }
