import { Status } from './../../../shared/model/status';
import { Car } from 'src/app/shared/model/car';
import { CarsService } from './../cars.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-car-new',
  templateUrl: './car-new.component.html',
  styleUrls: ['./car-new.component.css']
})
export class CarNewComponent implements OnInit {

  model: string;
  amount: number;
  bodyType: string;
  color: string;
  status: Status;
  yearOfProduction: number;
  mileage: number;
  make: string;

  constructor(public carsService: CarsService,
              private snackBar: MatSnackBar,
              private router: Router) { }

  ngOnInit() {
  }

  createCar() {
    // tslint:disable-next-line: max-line-length
    const car = new Car(null, this.make, this.model, this.bodyType, this.yearOfProduction, this.color, this.mileage, this.status, this.amount);
    this.carsService.saveCar(car)
      .subscribe(result => {
        this.snackBar.openFromComponent(SuccessSnackComponent, {
        duration: 2000,
        verticalPosition: 'top'
      });
    });
  }

  goToCars() {
    this.router.navigate(['/cars']);
  }

}

