import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rental } from 'src/app/shared/model/rental';
import { Employee } from 'src/app/shared/model/employee';

@Injectable({
  providedIn: 'root'
})
export class RentalsService {

  private readonly RENTAL_API = `${environment.serverApiUrl}/rental`;

  constructor(private httpClient: HttpClient) { }

  getAllRentals(): Observable<Rental[]> {
    return this.httpClient.get<Rental[]>(this.RENTAL_API);
  }

  getRentalById(id: number): Observable<Rental> {
    return this.httpClient.get<Rental>(this.RENTAL_API + `/${id}`);
  }

  addEmployee(id: number, employee: Employee): Observable<Rental> {
    return this.httpClient.put<Rental> (this.RENTAL_API + `/${id}` + `/employees`, employee);
  }

  deleteEmployee(id: number, rental: Rental): Observable<Rental> {
    return this.httpClient.put<Rental> (this.RENTAL_API + `/${id}`, rental);
  }

  updateRental(id: number, rental: Rental): Observable<Rental> {
    return this.httpClient.put<Rental> (this.RENTAL_API + `/${id}`, rental);
  }

}
