import { CustomerService } from './../customer.service';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/shared/model/customer';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-customer-overview',
  templateUrl: './customer-overview.component.html',
  styleUrls: ['./customer-overview.component.css']
})
export class CustomerOverviewComponent implements OnInit {

  customers: Observable<Customer[]>;
  customer: Observable<Customer>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    this.loadCustomers();
    this.displayedColumns = ['id', 'firstName', 'lastName', 'actions'];
  }

  private loadCustomers() {
    this.customers = this.customerService.getAllCustomers();
  }

  delete(id: number) {
    this.customerService.deleteCustomer(id).subscribe(
      data => {
        this.loadCustomers();
      },
      error => console.log(console.error()));
  }

}
