import { Customer } from './../../../shared/model/customer';

import { CustomerService } from './../customer.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { FormControl, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-customer-update',
  templateUrl: './customer-update.component.html',
  styleUrls: ['./customer-update.component.css']
})
export class CustomerUpdateComponent implements OnInit {

  customerForm = new FormGroup({
    firstNameForm: new FormControl('', Validators.required),
    lastNameForm: new FormControl('', Validators.required),
    emailForm: new FormControl('', [Validators.required, Validators.email]),
    addressForm: new FormControl('', Validators.required)
  });

  customerToUpdate = new Customer(null, this.customerFirstName.value, this.customerLastName.value,
    this.customerEmail.value, this.customerAdress.value);

  constructor(private router: Router,
              private route: ActivatedRoute,
              private customerService: CustomerService,
              private snackBar: MatSnackBar,
    ) { }

  ngOnInit() {
    this.customerService.getCustomerById(this.getIdFromRoute()).subscribe(
      result => this.customerToUpdate = result
    );
  }

  changeCustomer() {
    this.customerService.updateCustomer(this.getIdFromRoute(), this.customerToUpdate)
      .subscribe(result => {
        this.snackBar.openFromComponent(SuccessSnackComponent, {
          duration: 2000,
          verticalPosition: 'top'
        });
      });
  }

  getIdFromRoute(): number {
    return +this.route.snapshot.paramMap.get('id');
  }

  goToCustomerDetails() {
    this.router.navigate(['/customers', this.getIdFromRoute(), 'details']);
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
