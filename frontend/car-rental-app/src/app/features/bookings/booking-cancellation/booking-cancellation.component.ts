import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BookingsService } from '../bookings.service';
import { Booking } from 'src/app/shared/model/booking';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { ImpossibleSnackComponent } from 'src/app/shared/components/impossible-snack/impossible-snack.component';
import { BookingStatus } from 'src/app/shared/model/booking-status';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-booking-cancellation',
  templateUrl: './booking-cancellation.component.html',
  styleUrls: ['./booking-cancellation.component.css']
})
export class BookingCancellationComponent implements OnInit {

  status: BookingStatus;

  booking = new Booking(null, null, null, null, null, null, null, null, null, null, null, null);

  constructor(private route: ActivatedRoute,
              private router: Router,
              private bookingService: BookingsService,
              private snackBar: MatSnackBar,
            ) { }

  ngOnInit() {
    this.route.data
    .subscribe((data: { booking: Booking }) => {
      this.booking = data.booking;
    });

  }

  cancel() {
    if (this.booking.bookingStatus !== BookingStatus.open || (new Date().getTime() >= new Date(this.booking.dateFrom).getTime())) {
      this.snackBar.openFromComponent(ImpossibleSnackComponent, {
        duration: 2000,
        verticalPosition: 'top'
      });
      this.goBack();
    } else {
      this.bookingService.cancelBooking(this.getIdFromRoute()).subscribe();
      this.snackBar.openFromComponent(SuccessSnackComponent, {
        duration: 2000,
        verticalPosition: 'top'
      });
      this.booking.bookingStatus = BookingStatus.cancelled;
      this.goBack();
    }
  }

  goBack() {
    this.router.navigate(['../../', { id: this.booking.id}], { relativeTo: this.route });
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.route.snapshot.paramMap.get('id');
  }

}
