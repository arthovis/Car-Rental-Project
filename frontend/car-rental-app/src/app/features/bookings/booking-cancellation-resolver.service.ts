import { BookingsService } from './bookings.service';
import { Injectable } from '@angular/core';
import { Booking } from 'src/app/shared/model/booking';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingCancellationResolverService implements Resolve<Booking> {

  constructor(private bookinkService: BookingsService) { }

  resolve(route: ActivatedRouteSnapshot): Observable<Booking> {
    const id = +route.paramMap.get('id');

    return this.bookinkService.getBookingById(id);
  }

}
