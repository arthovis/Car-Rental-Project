import { CustomerService } from './../customer.service';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/shared/model/customer';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-customer-overview',
  templateUrl: './customer-overview.component.html',
  styleUrls: ['./customer-overview.component.css']
})
export class CustomerOverviewComponent implements OnInit {

  customers: Observable<Customer[]>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  pageEvent: PageEvent;

  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'firstName', 'lastName', 'actions'];
  }

  private loadFirstPage() {
    this.customers = this.customerService.getAllCustomers(this.pageIndex, this.pageSize);
  }

  delete(id: number) {
    this.customerService.deleteCustomer(id).subscribe(
      data => {
        this.getCustomers(this.pageEvent);
      },
      error => console.log(console.error()));
  }

  public getCustomers(event: PageEvent) {
    this.pageEvent = event;
    this.customers = this.customerService.getAllCustomers(event.pageIndex, event.pageSize);
  }

}
