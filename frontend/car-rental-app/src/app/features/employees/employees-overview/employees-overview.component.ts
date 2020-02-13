import { Observable } from 'rxjs';
import { EmployeesService } from './../employees.service';
import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/shared/model/employee';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-employees-overview',
  templateUrl: './employees-overview.component.html',
  styleUrls: ['./employees-overview.component.css']
})
export class EmployeesOverviewComponent implements OnInit {

  employees: Observable<Employee[]>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  pageEvent: PageEvent;

  constructor(private employeesService: EmployeesService) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'nameAndSurname', 'actions'];
  }

  private loadFirstPage() {
    this.employees = this.employeesService.getAllEmployees(this.pageIndex, this.pageSize);
  }

  delete(id: number) {
    this.employeesService.deleteEmployee(id).subscribe(
      data => {
        this.getEmployees(this.pageEvent);
      },
      error => console.log(error));
  }

  public getEmployees(event: PageEvent) {
    this.pageEvent = event;
    this.employees = this.employeesService.getAllEmployees(event.pageIndex, event.pageSize);
  }


}
