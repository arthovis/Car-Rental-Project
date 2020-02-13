import { Customer } from '../../shared/model/customer';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private readonly CUSTOMERG_API = `${environment.serverApiUrl}/customers`;

  constructor(private httpClient: HttpClient) { }

  getAllCustomers(pageIndex: number, pageSize: number): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(this.CUSTOMERG_API + `/?pageIndex=${pageIndex}&pageSize=${pageSize}`);
  }

  getCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(`${environment.serverApiUrl}/customers-list`);
  }

  getCustomerById(id: number): Observable<Customer> {
    return this.httpClient.get<Customer>(this.CUSTOMERG_API + `/${id}`);
  }

  saveCustomer(customer: Customer): Observable<Customer> {
    return this.httpClient.post<Customer>(this.CUSTOMERG_API, customer);
  }

  deleteCustomer(id: number): Observable<Customer> {
    return this.httpClient.delete<Customer>(this.CUSTOMERG_API + `/${id}`);
  }

  updateCustomer(id: number, customer: Customer): Observable<Customer> {
    return this.httpClient.put<Customer> (this.CUSTOMERG_API + `/${id}`, customer);
  }

}
