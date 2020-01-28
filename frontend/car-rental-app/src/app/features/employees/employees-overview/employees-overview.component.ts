import { Observable } from 'rxjs';
import { EmployeesService } from './../employees.service';
import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/shared/model/employee';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-employees-overview',
  templateUrl: './employees-overview.component.html',
  styleUrls: ['./employees-overview.component.css']
})
export class EmployeesOverviewComponent implements OnInit {

  employees: Observable<Employee[]>;
  employeeById: Observable<Employee>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(private employeesService: EmployeesService) { }

  ngOnInit() {
    this.loadEmployees();
    this.displayedColumns = ['id', 'nameAndSurname', 'actions'];
  }

  private loadEmployees() {
    this.employees = this.employeesService.getAllEmployees();
  }

  delete(id: number) {
    this.employeesService.deleteEmployee(id).subscribe(
      data => {
        this.loadEmployees();
      },
      error => console.log(error));
  }

}
