import { RentalOffice } from './../../shared/model/rentalOffice';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class RentalOfficeService {

  private readonly RENTAL_OFFICE_API = `${environment.serverApiUrl}/car-rental-offices`;

  constructor(private httpClient: HttpClient) { }

  saveRentalOffice(rentalOffice: RentalOffice): Observable<RentalOffice> {
    return this.httpClient.post<RentalOffice>(this.RENTAL_OFFICE_API, rentalOffice);
  }

  getAllRentalOffices(): Observable<RentalOffice[]> {
    return this.httpClient.get<RentalOffice[]> (this.RENTAL_OFFICE_API);
  }

}

