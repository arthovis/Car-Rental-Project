import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Booking } from 'src/app/shared/model/booking';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingsService {

  private readonly BOOKING_API = `${environment.serverApiUrl}/bookings`;

  constructor(private httpClient: HttpClient) { }

  saveBooking(booking: Booking): Observable<Booking> {
    return this.httpClient.post<Booking>(this.BOOKING_API, booking);
  }

  getAllBookings(): Observable<Booking[]> {
    return this.httpClient.get<Booking[]> (this.BOOKING_API);
  }

  getBookingById(id: number): Observable<Booking> {
    return this.httpClient.get<Booking> (this.BOOKING_API + `/${id}`);
  }

  deleteBooking(id: number): Observable<Booking> {
    return this.httpClient.delete<Booking> (this.BOOKING_API + `/${id}`);
  }

  updateBooking(id: number, booking: Booking): Observable<Booking> {
    return this.httpClient.put<Booking> (this.BOOKING_API + `/${id}`, booking);
  }

}
