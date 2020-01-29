import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from 'src/app/shared/model/car';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  private readonly CAR_API = `${environment.serverApiUrl}/cars`;

  constructor(private httpClient: HttpClient) { }

  getCarById(id: number): Observable<Car> {
    return this.httpClient.get<Car>(this.CAR_API + `/${id}`);
  }

  getAllCars(): Observable<Car[]> {
    return this.httpClient.get<Car[]>(this.CAR_API);
  }

  saveCar(car: Car): Observable<Car> {
    return this.httpClient.post<Car>(this.CAR_API, car);
  }

  deleteCar(id: number): Observable<Car> {
    return this.httpClient.delete<Car>(this.CAR_API + `/${id}`);
  }

  updateCar(id: number, car: Car): Observable<Car> {
    return this.httpClient.put<Car> (this.CAR_API + `/${id}`, car);
  }

}
