import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookingsRoutingModule } from './bookings-routing.module';
import { BookingOverviewComponent } from './booking-overview/booking-overview.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';
import { BoohingCancelationComponent } from './boohing-cancelation/boohing-cancelation.component';


@NgModule({
  declarations: [BookingOverviewComponent, BookingDetailsComponent, BoohingCancelationComponent],
  imports: [
    CommonModule,
    BookingsRoutingModule
  ]
})
export class BookingsModule { }
