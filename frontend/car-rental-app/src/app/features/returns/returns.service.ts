import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Return } from 'src/app/shared/model/return';
import { Employee } from 'src/app/shared/model/employee';

@Injectable({
  providedIn: 'root'
})
export class ReturnsService {

  private readonly RETURN_API = `${environment.serverApiUrl}/car-return`;

  constructor(private httpClient: HttpClient) { }

  getAllReturns(): Observable<Return[]> {
    return this.httpClient.get<Return[]>(this.RETURN_API);
  }

  getReturnById(id: number): Observable<Return> {
    return this.httpClient.get<Return>(this.RETURN_API + `/${id}`);
  }

  addEmployee(id: number, employee: Employee): Observable<Return> {
    return this.httpClient.put<Return>(this.RETURN_API + `/${id}` + `/employees`, employee);
  }

  deleteEmployee(id: number, carReturn: Return): Observable<Return> {
    return this.httpClient.put<Return>(this.RETURN_API + `/${id}`, carReturn);
  }

  updateReturn(id: number, carReturn: Return): Observable<Return> {
    return this.httpClient.put<Return>(this.RETURN_API + `/${id}`, carReturn);
  }

}
