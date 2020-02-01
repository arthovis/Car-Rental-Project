import { BranchesService } from './../../branches/branches.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Rental } from 'src/app/shared/model/rental';
import { Employee } from 'src/app/shared/model/employee';
import { RentalsService } from '../rentals.service';
import { Router, ActivatedRoute } from '@angular/router';
import { map, flatMap } from 'rxjs/operators';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-rentals-details',
  templateUrl: './rentals-details.component.html',
  styleUrls: ['./rentals-details.component.css']
})
export class RentalsDetailsComponent implements OnInit {

  rentals: Observable<Rental[]>;
  rental: Rental;
  employees: Employee[];
  view1: false;

  displayedColumns: string[];

  constructor(private rentalService: RentalsService,
              private branchService: BranchesService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private snackBar: MatSnackBar
            ) { }

  ngOnInit() {
    this.loadRentalDetails();
    this.displayedColumns = ['id', 'rentalDate', 'comments', 'employeeDto', 'branchDto', 'actions'];
  }

  loadRentalDetails() {
    this.rentals = this.activatedRoute.params.pipe(
      // tslint:disable-next-line: no-string-literal
      map(params => params['id']),
      flatMap(id => this.rentalService.getRentalById(id)),
      map(cro => [cro]));

    this.rentals.subscribe(rentals => {
      this.rental = rentals[0];
    });
  }

  goBack() {
    this.router.navigate(['/rentals']);
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.activatedRoute.snapshot.paramMap.get('id');
  }

  listBranchEmployees(id: number) {
    this.branchService.getBranchById(id).subscribe(branch =>
      this.employees = branch.employeeList
    );
  }

  addEmployee(employee: Employee) {
    this.rentalService.addEmployee(this.getIdFromRoute(), employee).subscribe();
    this.snackBar.openFromComponent(SuccessSnackComponent, {
      duration: 2000,
      verticalPosition: 'top'
    });
    this.loadRentalDetails();
  }

  deleteEmployee() {
    this.rental.employeeDto = null;
    this.rentalService.deleteEmployee(this.getIdFromRoute(), this.rental).subscribe();
    this.snackBar.openFromComponent(SuccessSnackComponent, {
      duration: 2000,
      verticalPosition: 'top'
    });
    this.loadRentalDetails();
  }

}
