import { ReturnsService } from './../returns.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { Return } from 'src/app/shared/model/return';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-returns-overview',
  templateUrl: './returns-overview.component.html',
  styleUrls: ['./returns-overview.component.css']
})
export class ReturnsOverviewComponent implements OnInit {

  returns: Observable<Return[]>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  constructor(private returnService: ReturnsService) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'dateOfReturn', 'employeeDto', 'additionalPayment', 'actions'];
  }

  private loadFirstPage() {
    this.returns = this.returnService.getAllReturns(this.pageIndex, this.pageSize);
  }

  public getReturns(event: PageEvent) {
    this.returns = this.returnService.getAllReturns(event.pageIndex, event.pageSize);
  }

}
