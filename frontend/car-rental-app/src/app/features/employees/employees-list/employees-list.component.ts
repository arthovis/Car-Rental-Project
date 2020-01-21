import { Employee } from './../../../shared/model/employee';
import { BranchesService } from './../../branches/branches.service';
import { EmployeesService } from './../employees.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrls: ['./employees-list.component.css']
})
export class EmployeesListComponent implements OnInit {

  employees: Observable<Employee[]>;
  employeeById: Employee;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(private employeeService: EmployeesService,
              private branchService: BranchesService,
              private route: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar
              ) { }

  ngOnInit() {
    this.loadEmployees();
    this.displayedColumns = ['id', 'nameAndSurname', 'actions'];
  }

  loadEmployees() {
    this.employees = this.employeeService.getAllEmployees();
  }

  addEmployee(id: number): void {
    this.employeeService.getEmployeeById(id)
      .subscribe(employee => {
        this.employeeById = employee;
        this.branchService.addEmployee(this.getIdFromRoute(), this.employeeById).subscribe();
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
