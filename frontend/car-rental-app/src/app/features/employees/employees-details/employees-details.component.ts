import { EmployeesService } from './../employees.service';
import { Branch } from './../../../shared/model/branch';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/shared/model/employee';
import { BranchesService } from '../../branches/branches.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { map, flatMap } from 'rxjs/operators';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-employees-details',
  templateUrl: './employees-details.component.html',
  styleUrls: ['./employees-details.component.css']
})
export class EmployeesDetailsComponent implements OnInit {

  employees: Observable<Employee[]>;
  employee: Employee;
  branch: Branch;
  view1: false;

  displayedColumns: string[];

  constructor(private employeeService: EmployeesService,
              private branchService: BranchesService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  loadEmployeeDetails() {
    this.employees = this.activatedRoute.params.pipe(
      // tslint:disable-next-line: no-string-literal
      map(params => params['id']),
      flatMap(id => this.employeeService.getEmployeeById(id)),
      map(cro => [cro]));

    this.employees. subscribe(employees => {
        this.employee = this.employees[0];
        this.branch = this.employee.branchDto;
      });
  }

  goBack() {
    this.router.navigate(['/employees']);
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.activatedRoute.snapshot.paramMap.get('id');
  }

  listEmployeeBranch(id: number) {
    this.employeeService.getEmployeeById(id).subscribe(employee =>
      this.branch = employee.branchDto);
  }

  addBranch(branch: Branch) {
    this.employeeService.addBranch(this.getIdFromRoute(), branch).subscribe();
    this.snackBar.openFromComponent(SuccessSnackComponent, {
      duration: 2000,
      verticalPosition: 'top'
    });
    this.loadEmployeeDetails();
  }
}
