import { Branch } from 'src/app/shared/model/branch';
import { RentalOfficeService } from './rental-office.service';
import { Component, OnInit } from '@angular/core';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-rental-office',
  templateUrl: './rental-office.component.html',
  styleUrls: ['./rental-office.component.css']
})
export class RentalOfficeComponent implements OnInit {

  name: string;

  internetDomain: string;

  contactAddress: string;

  owner: string;

  logoType: string;

  branches: Branch[];

  constructor(public rentalOfficeService: RentalOfficeService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  createRentalOffice() {
    const rentalOffice = new RentalOffice(null, this.name, this.internetDomain,
        this.contactAddress, this.owner, this.logoType, this.branches);
    this.rentalOfficeService.saveRentalOffice(rentalOffice)
      .subscribe(result => {
        this.snackBar.openFromComponent(SuccessSnackComponent, {
          duration: 2000,
          verticalPosition: 'top'
        });
      });
  }

}


