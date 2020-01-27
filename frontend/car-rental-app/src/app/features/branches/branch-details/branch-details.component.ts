import { BranchesService } from './../branches.service';
import { Branch } from './../../../shared/model/branch';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/shared/model/employee';
import { Car } from 'src/app/shared/model/car';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { map, flatMap } from 'rxjs/operators';

@Component({
  selector: 'app-branch-details',
  templateUrl: './branch-details.component.html',
  styleUrls: ['./branch-details.component.css']
})
export class BranchDetailsComponent implements OnInit {

  branches: Observable<Branch[]>;
  branch: Branch;
  employees: Employee[];
  cars: Car[];
  view1: boolean;
  view2: boolean;

  displayedColumns: string[];

  constructor(private branchService: BranchesService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar
              ) { }

  ngOnInit() {
    this.loadBranchDetails();
    this.displayedColumns = ['id', 'address', 'employeeList', 'availableCarsList', 'actions'];
  }

  loadBranchDetails() {
    this.branches = this.activatedRoute.params.pipe(
      // tslint:disable-next-line: no-string-literal
      map(params => params['id']),
      flatMap(id => this.branchService.getBranchById(id)),
      map(cro => [cro]));

    this.branches.subscribe(branches => {
      this.branch = branches[0];
      this.employees = this.branch.employeeList;
      this.cars = this.branch.availableCarsList;
    });
  }

  goBack() {
    this.router.navigate(['/branches']);
  }

  deleteEmployee(employee: Employee): void {
    this.branchService.deleteEmployee(this.getIdFromRoute(), employee)
    .subscribe(data => {
      this.loadBranchDetails();
      this.snackBar.openFromComponent(SuccessSnackComponent, {
        duration: 2000,
        verticalPosition: 'top'
      });
    });
  }

  deleteCar(car: Car): void {
    this.branchService.deleteCar(this.getIdFromRoute(), car)
    .subscribe(data => {
      this.loadBranchDetails();
      this.snackBar.openFromComponent(SuccessSnackComponent, {
        duration: 2000,
        verticalPosition: 'top'
      });
    });
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.activatedRoute.snapshot.paramMap.get('id');
  }

}
