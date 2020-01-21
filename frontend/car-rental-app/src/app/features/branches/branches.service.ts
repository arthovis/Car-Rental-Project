import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Branch } from 'src/app/shared/model/branch';

@Injectable({
  providedIn: 'root'
})
export class BranchesService {

  private readonly BRANCH_API = `${environment.serverApiUrl}/branch`;

  constructor(private httpClient: HttpClient) { }

  getAllBranches(): Observable<Branch[]> {
    return this.httpClient.get<Branch[]>(this.BRANCH_API);
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

}
