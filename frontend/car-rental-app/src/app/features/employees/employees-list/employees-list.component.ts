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
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrls: ['./employees-list.component.css']
})
export class EmployeesListComponent implements OnInit {

  employees: Observable<Employee[]>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  constructor(private employeeService: EmployeesService,
              private branchService: BranchesService,
              private route: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar
              ) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'nameAndSurname', 'actions'];
  }

  loadFirstPage() {
    this.employees = this.employeeService.getAllEmployees(this.pageIndex, this.pageSize);
  }

  public getEmployees(event: PageEvent) {
    this.employees = this.employeeService.getAllEmployees(event.pageIndex, event.pageSize);
  }

  addEmployee(id: number): void {
    this.employeeService.getEmployeeById(id)
      .subscribe(employee => {
        this.branchService.addEmployee(this.getIdFromRoute(), employee).subscribe();
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
