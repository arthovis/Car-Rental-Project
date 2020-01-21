import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { RentalOfficesService } from '../rental-offices.service';
import { Branch } from 'src/app/shared/model/branch';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-rental-office-new',
  templateUrl: './rental-office-new.component.html',
  styleUrls: ['./rental-office-new.component.css']
})
export class RentalOfficeNewComponent implements OnInit {

  name: string;

  internetDomain: string;

  contactAddress: string;

  owner: string;

  logoType: string;

  branches: Branch[];

  constructor(private rentalOfficeService: RentalOfficesService,
              private snackBar: MatSnackBar,
              private router: Router
              ) { }

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

  goToRentalOffices() {
    this.router.navigate(['/rental-offices']);
  }

}


