import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Car } from 'src/app/shared/model/car';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { CarsService } from '../cars.service';
import { BranchesService } from '../../branches/branches.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-cars-list',
  templateUrl: './cars-list.component.html',
  styleUrls: ['./cars-list.component.css']
})
export class CarsListComponent implements OnInit {

  cars: Observable<Car[]>;
  carById: Car;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(private carService: CarsService,
              private branchService: BranchesService,
              private route: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar
              ) { }

  ngOnInit() {
    this.loadCars();
    this.displayedColumns = ['id', 'model', 'actions'];
  }

  loadCars() {
    this.cars = this.carService.getAllCars();
  }

  addCar(id: number): void {
    this.carService.getCarById(id)
      .subscribe(car => {
        this.carById = car;
        this.branchService.addCar(this.getIdFromRoute(), this.carById).subscribe();
        this.snackBar.openFromComponent(SuccessSnackComponent, {
          duration: 2000,
          verticalPosition: 'top'
        });
      });
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.route.snapshot.paramMap.get('id');
  }

  goToBranch() {
    this.router.navigate(['/branches', this.getIdFromRoute(), 'details']);
  }

}
