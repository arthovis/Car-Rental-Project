import { SuccessSnackComponent } from './../../../shared/components/success-snack/success-snack.component';
import { Customer } from './../../../shared/model/customer';
import { CustomerService } from './../customer.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { FormControl, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-customer-new',
  templateUrl: './customer-new.component.html',
  styleUrls: ['./customer-new.component.css']
})
export class CustomerNewComponent implements OnInit {

  customerForm = new FormGroup({
    firstNameForm: new FormControl('', Validators.required),
    lastNameForm: new FormControl('', Validators.required),
    emailForm: new FormControl('', [Validators.required, Validators.email]),
    addressForm: new FormControl('', Validators.required)
  });

  constructor(public customerService: CustomerService,
              private snackBar: MatSnackBar,
              private router: Router) { }

  ngOnInit() {
  }

  createCustomer() {
    // tslint:disable-next-line: max-line-length
    const customer = new Customer(null, this.customerFirstName.value, this.customerLastName.value,
      this.customerEmail.value, this.customerAdress.value);

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

  get customerFirstName() {
    return this.customerForm.get('firstNameForm');
  }

  get customerLastName() {
    return this.customerForm.get('lastNameForm');
  }

  get customerEmail() {
    return this.customerForm.get('emailForm');
  }

  get customerAdress() {
    return this.customerForm.get('addressForm');
  }

  getErrorFirstNameMessage() {
    return this.customerFirstName.hasError('required') ? 'You must enter a name' :
      '';
  }

  getErrorLastNameMessage() {
    return this.customerLastName.hasError('required') ? 'You must enter a name' :
      '';
  }

  getErrorEmailMessage() {
    return this.customerEmail.hasError('required') ? 'You must enter a email' :
      this.customerEmail.hasError('email') ? 'Not a valid email' :
        '';
  }

  getErrorAdressMessage() {
    return this.customerAdress.hasError('required') ? 'You must enter an adress' :
      '';
  }

}
