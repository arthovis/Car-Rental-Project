import { BookingsService } from './../bookings.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking } from 'src/app/shared/model/booking';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { PageEvent, MatPaginator } from '@angular/material/paginator';

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

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  pageEvent: PageEvent;

  constructor(private bookingService: BookingsService,
              private route: ActivatedRoute
            ) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'dateOfBooking', 'dateFrom', 'dateTo', 'customerDto', 'amount', 'bookingStatus', 'actions'];
  }

  loadFirstPage() {
    this.bookings = this.route.paramMap.pipe(
      switchMap(params => {
          return this.bookingService.getAllBookings(this.pageIndex, this.pageSize);
      })
    );
  }

  delete(id: number) {
    this.bookingService.deleteBooking(id).subscribe(
      data => {
        this.getBookings(this.pageEvent);
      },
      error => console.log(error));
  }

  public getBookings(event: PageEvent) {
    this.pageEvent = event;
    this.bookings = this.route.paramMap.pipe(
      switchMap(params => {
        return this.bookingService.getAllBookings(event.pageIndex, event.pageSize);
      })
    );
  }

}
