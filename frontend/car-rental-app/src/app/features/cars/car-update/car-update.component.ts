import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { CarsService } from './../cars.service';
import { Component, OnInit } from '@angular/core';
import { Car } from 'src/app/shared/model/car';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-car-update',
  templateUrl: './car-update.component.html',
  styleUrls: ['./car-update.component.css']
})
export class CarUpdateComponent implements OnInit {

  carToUpdate = new Car(null, null, null, null, null, null, null, null, null);

  constructor(private router: Router,
              private route: ActivatedRoute,
              private carsService: CarsService,
              private snackBar: MatSnackBar,
              ) { }

  ngOnInit() {
    this.carsService.getCarById(this.getIdFromRoute()).subscribe(
      result => this.carToUpdate = result
    );
  }

  changeCar() {
    this.carsService.updateCar(this.getIdFromRoute(), this.carToUpdate)
      .subscribe(result => {
        this.snackBar.openFromComponent(SuccessSnackComponent, {
          duration: 2000,
          verticalPosition: 'top'
        });
      });
  }

  getIdFromRoute(): number {
    return +this.route.snapshot.paramMap.get('id');
  }

  goToCarDetails() {
    this.router.navigate(['/cars', this.getIdFromRoute(), 'details']);
  }

}
