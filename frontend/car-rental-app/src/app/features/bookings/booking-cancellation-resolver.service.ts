import { BookingsService } from './bookings.service';
import { Injectable } from '@angular/core';
import { Booking } from 'src/app/shared/model/booking';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, EMPTY, of } from 'rxjs';
import { take, mergeMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BookingCancellationResolverService implements Resolve<Booking> {

  constructor(private bookinkService: BookingsService,
              private router: Router
              ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Booking> | Observable<never> {
    const id = +route.paramMap.get('id');

    return this.bookinkService.getBookingById(id).pipe(
      take(1),
      mergeMap(booking => {
        if (booking) {
          return of(booking);
        } else { // id not found
          this.router.navigate(['/bookings']);
          return EMPTY;
        }
      })
    );
  }

}
