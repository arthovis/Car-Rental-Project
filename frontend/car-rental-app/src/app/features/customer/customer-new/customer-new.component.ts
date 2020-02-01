import { SuccessSnackComponent } from './../../../shared/components/success-snack/success-snack.component';
import { Customer } from './../../../shared/model/customer';
import { CustomerService } from './../customer.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-new',
  templateUrl: './customer-new.component.html',
  styleUrls: ['./customer-new.component.css']
})
export class CustomerNewComponent implements OnInit {

  firstName: string;
  lastName: string;
  email: string;
  address: string;

  constructor(public customerService: CustomerService,
              private snackBar: MatSnackBar,
              private router: Router ) { }

  ngOnInit() {
  }

  createCustomer() {
        // tslint:disable-next-line: max-line-length
        const customer = new Customer(null, this.firstName, this.lastName, this.email, this.address);
        this.customerService.saveCustomer(customer)
          .subscribe(result => {
              this.snackBar.openFromComponent(SuccessSnackComponent, {
                duration: 2000,
                verticalPosition: 'top'
              });
          });
  }


  goToCustomers() {
    this.router.navigate(['/customers']);
  }
}
