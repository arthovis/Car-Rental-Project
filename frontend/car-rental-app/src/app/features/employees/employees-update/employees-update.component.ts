import { SuccessSnackComponent } from './../../../shared/components/success-snack/success-snack.component';
import { EmployeesService } from './../employees.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { Employee } from 'src/app/shared/model/employee';

@Component({
  selector: 'app-employees-update',
  templateUrl: './employees-update.component.html',
  styleUrls: ['./employees-update.component.css']
})
export class EmployeesUpdateComponent implements OnInit {

  employeeToUpdate = new Employee(null, null, null, null);

  constructor(private router: Router,
              private route: ActivatedRoute,
              private employeeService: EmployeesService,
              private snackBar: MatSnackBar,
              ) { }

  ngOnInit() {
    this.employeeService.getEmployeeById(this.getIdFromRoute()).subscribe(
      result => this.employeeToUpdate = result
    );
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.route.snapshot.paramMap.get('id');
  }

  changeEmployee() {
    this.employeeService.updateEmployee(this.getIdFromRoute(), this.employeeToUpdate)
    .subscribe(result => {
      this.snackBar.openFromComponent(SuccessSnackComponent, {
        duration: 2000,
        verticalPosition: 'top'
      });
    });
  }

  goToEmployeeDetails() {
    this.router.navigate(['/employees', this.getIdFromRoute(), 'details']);
  }

}
