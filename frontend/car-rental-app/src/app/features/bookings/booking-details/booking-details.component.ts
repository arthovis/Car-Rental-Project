import { BookingsService } from './../bookings.service';
import { Booking } from 'src/app/shared/model/booking';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { map, flatMap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.css']
})
export class BookingDetailsComponent implements OnInit {

  bookings: Observable<Booking[]>;
  booking: Booking;

  displayedColumns: string[];

  constructor(private bookingService: BookingsService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.loadBookingDetails();
    this.displayedColumns = ['id', 'customerDto', 'dateOfBooking', 'dateFrom', 'dateTo', 'rentalBranchDto',
      'returnBranchDto', 'carDto', 'rentalDto', 'carReturnDto', 'amount', 'bookingStatus', 'actions'];
  }

  loadBookingDetails() {
    this.bookings = this.activatedRoute.params.pipe(
      // tslint:disable-next-line: no-string-literal
      map(params => params['id']),
      flatMap(id => this.bookingService.getBookingById(id)),
      map(cro => [cro]));

    this.bookings.subscribe(bookings => {
      this.booking = bookings[0];
    });
  }

  goBack() {
    this.router.navigate(['/rentals']);
  }

}
