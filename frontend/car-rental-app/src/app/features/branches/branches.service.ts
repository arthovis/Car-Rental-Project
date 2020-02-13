import { Employee } from './../../shared/model/employee';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Branch } from 'src/app/shared/model/branch';
import { Car } from 'src/app/shared/model/car';

@Injectable({
  providedIn: 'root'
})
export class BranchesService {

  private readonly BRANCH_API = `${environment.serverApiUrl}/branch`;

  constructor(private httpClient: HttpClient) { }

  getAllBranches(pageIndex: number, pageSize: number): Observable<Branch[]> {
    return this.httpClient.get<Branch[]>(this.BRANCH_API + `/?pageIndex=${pageIndex}&pageSize=${pageSize}`);
  }

  getBranches(): Observable<Branch[]> {
    return this.httpClient.get<Branch[]>(`${environment.serverApiUrl}/branches-list`);
  }

  saveBranch(branch: Branch): Observable<Branch> {
    return this.httpClient.post<Branch>(this.BRANCH_API, branch);
  }

  deleteBranch(id: number): Observable<any> {
    return this.httpClient.delete(this.BRANCH_API + `/${id}`);
  }

  getBranchById(id: number): Observable<Branch> {
    return this.httpClient.get<Branch>(this.BRANCH_API + `/${id}`);
  }

  updateBranch(id: number, branch: Branch): Observable<Branch> {
    return this.httpClient.put<Branch> (this.BRANCH_API + `/${id}`, branch);
  }

  addEmployee(id: number, employee: Employee): Observable<Branch> {
    return this.httpClient.put<Branch> (this.BRANCH_API + `/${id}` + `/employees`, employee);
  }

  deleteEmployee(id: number, employee: Employee): Observable<Branch> {
    return this.httpClient.put<Branch> (this.BRANCH_API + `/${id}` + `/details`, employee);
  }

  addCar(id: number, car: Car): Observable<Branch> {
    return this.httpClient.put<Branch> (this.BRANCH_API + `/${id}` + `/cars`, car);
  }

  deleteCar(id: number, car: Car): Observable<Branch> {
    return this.httpClient.post<Branch> (this.BRANCH_API + `/${id}` + `/details`, car);
  }

}
