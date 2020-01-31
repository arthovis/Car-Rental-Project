import { BookingsComponent } from './bookings.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookingOverviewComponent } from './booking-overview/booking-overview.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';
import { BookingNewComponent } from './booking-new/booking-new.component';
import { BookingCancelationComponent } from './booking-cancelation/booking-cancelation.component';


const routes: Routes = [
  {
    path: 'bookings',
    component: BookingsComponent,
    children: [
      {
        path: '',
        component: BookingOverviewComponent
      },
    ]
  },
  {
    path: 'bookings/:id/details',
    component: BookingDetailsComponent,
    children: [
      {
        path: 'bookings/:id/cancelation',
        component: BookingCancelationComponent
      },
    ]
  },
  {
    path: 'bookings/new',
    component: BookingNewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookingsRoutingModule { }
