import { CarsService } from './../cars.service';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Car } from 'src/app/shared/model/car';
import { faSearchPlus, faTrash, IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { PageEvent } from '@angular/material/paginator';


@Component({
  selector: 'app-car-overview',
  templateUrl: './car-overview.component.html',
  styleUrls: ['./car-overview.component.css']
})
export class CarOverviewComponent implements OnInit {

  cars: Observable<Car[]>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  pageEvent: PageEvent;

  constructor(private carService: CarsService) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'model', 'actions'];
  }

  private loadFirstPage() {
    this.cars = this.carService.getAllCars(this.pageIndex, this.pageSize);
  }

  delete(id: number) {
    this.carService.deleteCar(id).subscribe(
      data => {
        this.getCars(this.pageEvent);
      },
      error => console.log(console.error()));
  }

  public getCars(event: PageEvent) {
    this.pageEvent = event;
    this.cars = this.carService.getAllCars(event.pageIndex, event.pageSize);
  }

}
