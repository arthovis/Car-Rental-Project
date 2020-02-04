import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Revenue } from 'src/app/shared/model/revenue';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RevenuesService {

  private readonly REVENUE_API = `${environment.serverApiUrl}/revenues`;

  constructor(private httpClient: HttpClient) { }

  getRevenue(id: number): Observable<Revenue> {
    return this.httpClient.get<Revenue>(this.REVENUE_API + `/?branchID=${id}`);
  }

}
