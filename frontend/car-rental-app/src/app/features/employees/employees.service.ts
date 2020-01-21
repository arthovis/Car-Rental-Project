import { Employee } from './../../shared/model/employee';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {

  private readonly EMPLOYEE_API = `${environment.serverApiUrl}/employees`;

  constructor(private httpClient: HttpClient) { }

  getEmployeeById(id: number): Observable<Employee> {
    return this.httpClient.get<Employee>(this.EMPLOYEE_API + `/${id}`);
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.EMPLOYEE_API);
  }

}
