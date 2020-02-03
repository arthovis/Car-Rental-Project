import { BookingsService } from './../bookings.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking } from 'src/app/shared/model/booking';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-booking-overview',
  templateUrl: './booking-overview.component.html',
  styleUrls: ['./booking-overview.component.css']
})
export class BookingOverviewComponent implements OnInit {

  bookings: Observable<Booking[]>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(private bookingService: BookingsService,
              private route: ActivatedRoute
              ) { }

  ngOnInit() {
    this.loadBookings();
    this.displayedColumns = ['id', 'dateOfBooking', 'dateFrom', 'dateTo', 'customerDto', 'amount', 'bookingStatus', 'actions'];
  }

  loadBookings() {
    this.bookings = this.route.paramMap.pipe(
      switchMap(params => {
        return this.bookingService.getAllBookings();
      })
    );
  }

  delete(id: number) {
    this.bookingService.deleteBooking(id).subscribe(
      data => {
        this.loadBookings();
      },
      error => console.log(error));
  }

}
