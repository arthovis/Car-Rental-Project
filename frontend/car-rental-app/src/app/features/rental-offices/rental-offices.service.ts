import { Branch } from 'src/app/shared/model/branch';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';


@Injectable({
  providedIn: 'root'
})
export class RentalOfficesService {

  private readonly RENTAL_OFFICE_API = `${environment.serverApiUrl}/car-rental-offices`;

  constructor(private httpClient: HttpClient) { }

  saveRentalOffice(rentalOffice: RentalOffice): Observable<RentalOffice> {
    return this.httpClient.post<RentalOffice>(this.RENTAL_OFFICE_API, rentalOffice);
  }

  getAllRentalOffices(pageIndex: number, pageSize: number): Observable<RentalOffice[]> {
    return this.httpClient.get<RentalOffice[]> (this.RENTAL_OFFICE_API + `/?pageIndex=${pageIndex}&pageSize=${pageSize}`);
  }

  getCarRentalOfficeById(id: number): Observable<RentalOffice> {
    return this.httpClient.get<RentalOffice> (this.RENTAL_OFFICE_API + `/${id}`);
  }

  updateRentalOffice(id: number, rentalOffice: RentalOffice): Observable<RentalOffice> {
    return this.httpClient.put<RentalOffice> (this.RENTAL_OFFICE_API + `/${id}`, rentalOffice);
  }

  deleteRentalOffice(id: number): Observable<RentalOffice> {
    return this.httpClient.delete<RentalOffice> (this.RENTAL_OFFICE_API + `/${id}`);
  }

  addBranch(id: number, branch: Branch): Observable<RentalOffice> {
    return this.httpClient.put<RentalOffice> (this.RENTAL_OFFICE_API + `/${id}` + `/branches`, branch);
  }

  deleteBranch(id: number, branch: Branch): Observable<RentalOffice> {
    return this.httpClient.put<RentalOffice> (this.RENTAL_OFFICE_API + `/${id}` + `/details`, branch);
  }

}

