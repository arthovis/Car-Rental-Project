import { SuccessSnackComponent } from './../../../shared/components/success-snack/success-snack.component';
import { Employee } from 'src/app/shared/model/employee';
import { EmployeesService } from './../employees.service';
import { JobPosition } from './../../../shared/model/job-position';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employees-new',
  templateUrl: './employees-new.component.html',
  styleUrls: ['./employees-new.component.css']
})
export class EmployeesNewComponent implements OnInit {

  nameAndSurname: string;
  jobPosition: JobPosition;


  constructor(private employeesService: EmployeesService,
              private snackBar: MatSnackBar,
              private router: Router) { }

  ngOnInit() {
  }

  createEmployee() {
    // tslint:disable-next-line: max-line-length
    const employee = new Employee(null, this.nameAndSurname, this.jobPosition, null);
    this.employeesService.saveEmployee(employee)
    .subscribe(result => {
      this.snackBar.openFromComponent(SuccessSnackComponent, {
        duration: 2000,
        verticalPosition: 'top'
      });
    });
  }

  goToEmployees() {
    this.router.navigate(['/employees']);
  }

}
