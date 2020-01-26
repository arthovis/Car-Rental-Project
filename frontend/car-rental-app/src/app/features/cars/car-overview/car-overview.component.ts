import { CarsService } from './../cars.service';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Car } from 'src/app/shared/model/car';
import { faSearchPlus, faTrash, IconDefinition } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-car-overview',
  templateUrl: './car-overview.component.html',
  styleUrls: ['./car-overview.component.css']
})
export class CarOverviewComponent implements OnInit {

  cars: Observable<Car[]>;
  carById: Observable<Car>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(private carsService: CarsService) { }

  ngOnInit() {
    this.loadCars();
    this.displayedColumns = ['id', 'model', 'actions'];
  }

  private loadCars() {
    this.cars = this.carsService.getAllCars();
  }

  delete(id: number) {
    this.carsService.deleteCar(id).subscribe(
      data => {
        this.loadCars();
      },
      error => console.log(console.error()));
  }
}
