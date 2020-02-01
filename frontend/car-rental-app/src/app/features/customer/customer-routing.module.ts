import { CustomerComponent } from './customer.component';
import { CustomerOverviewComponent } from './customer-overview/customer-overview.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { CustomerNewComponent } from './customer-new/customer-new.component';


const routes: Routes = [
  {
    path: 'customers',
    component: CustomerComponent,
    children: [
      {
      path: '',
      component: CustomerOverviewComponent
      },
    ]
  },
  {
    path: 'customers/new',
    component: CustomerNewComponent
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
export class CustomerRoutingModule { }
