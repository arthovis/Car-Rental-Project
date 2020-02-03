import { BookingsComponent } from './bookings.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatAutocompleteModule, MatDatepickerModule } from '@angular/material';
import { MatMomentDateModule, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';

import { BookingsRoutingModule } from './bookings-routing.module';
import { BookingOverviewComponent } from './booking-overview/booking-overview.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';
import { BookingNewComponent } from './booking-new/booking-new.component';
import { BookingCancellationComponent } from './booking-cancellation/booking-cancellation.component';


@NgModule({
  declarations: [
    BookingsComponent,
    BookingOverviewComponent,
    BookingDetailsComponent,
    BookingCancellationComponent,
    BookingNewComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    FontAwesomeModule,
    MatAutocompleteModule,
    MatDatepickerModule,
    MatMomentDateModule, // fixeaza problema datei cu o zi in urma
    BookingsRoutingModule
  ],

  // fixeaza problema datei cu o zi in urma
  providers: [
    {provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: {useUtc: true}}
  ]
})
export class BookingsModule { }
