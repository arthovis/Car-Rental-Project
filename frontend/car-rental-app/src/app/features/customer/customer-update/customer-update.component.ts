import { Customer } from './../../../shared/model/customer';

import { CustomerService } from './../customer.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-customer-update',
  templateUrl: './customer-update.component.html',
  styleUrls: ['./customer-update.component.css']
})
export class CustomerUpdateComponent implements OnInit {

  customerToUpdate = new Customer(null, null, null, null, null);

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

}
