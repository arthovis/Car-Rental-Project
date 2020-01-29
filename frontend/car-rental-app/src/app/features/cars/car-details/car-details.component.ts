import { CarsService } from './../cars.service';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Car } from 'src/app/shared/model/car';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { flatMap, map } from 'rxjs/operators';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {

  cars: Observable<Car[]>;
  car: Car;
  view1: boolean;
  view2: boolean;

  displayedColumns: string[];

  constructor(private carService: CarsService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              ) { }

  ngOnInit() {
    this.loadCarDetails();
    this.displayedColumns = ['id', 'make', 'model', 'bodyType', 'yearOfProduction', 'color', 'mileage', 'status', 'amount', 'actions']
  }

  loadCarDetails() {
    this.cars = this.activatedRoute.params.pipe(
      // tslint:disable-next-line: no-string-literal
      map(params => params['id']),
      flatMap(id => this.carService.getCarById(id)),
      map(cro => [cro]));

    this.cars.subscribe(cars => {
        this.car = cars[0];
      });
  }

  goBack() {
    this.router.navigate(['/cars']);
  }

}
