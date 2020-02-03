import { BookingsComponent } from './bookings.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookingOverviewComponent } from './booking-overview/booking-overview.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';
import { BookingNewComponent } from './booking-new/booking-new.component';
import { BookingCancellationComponent } from './booking-cancellation/booking-cancellation.component';
import { BookingCancellationResolverService } from './booking-cancellation-resolver.service';


const routes: Routes = [
  {
    path: 'bookings',
    component: BookingsComponent,
    children: [
      {
        path: '',
        component: BookingOverviewComponent,
        children: [
          {
            path: ':id/cancellations',
            component: BookingCancellationComponent,
            resolve: {
              booking: BookingCancellationResolverService
            }
          },
        ]
      },
    ]
  },
  {
    path: 'bookings/:id/details',
    component: BookingDetailsComponent,
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
