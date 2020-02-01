import { CustomerService } from './../customer.service';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/shared/model/customer';
import { ActivatedRoute, Router } from '@angular/router';
import { map, flatMap } from 'rxjs/operators';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  customers: Observable<Customer[]>;
  customer: Customer;
  view1: boolean;
  view2: boolean;

  displayedColumns: string[];

  constructor(private customerService: CustomerService,
              private activatedRoute: ActivatedRoute,
              private router: Router, ) { }

  ngOnInit() {
    this.loadCustomerDetails();
    this.displayedColumns = ['id', 'firstName', 'lastName', 'email', 'address', 'actions'];
  }

  loadCustomerDetails() {
    this.customers = this.activatedRoute.params.pipe(
      // tslint:disable-next-line: no-string-literal
      map(params => params['id']),
      flatMap(id => this.customerService.getCustomerById(id)),
      map(cro => [cro]));

    this.customers.subscribe(customers => {
        this.customer = customers[0];
      });
  }

  goBack() {
    this.router.navigate(['/customers']);
  }

}
