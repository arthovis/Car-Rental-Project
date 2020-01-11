import { RentalOfficeService } from './rental-office.service';
import { Component, OnInit } from '@angular/core';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';

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

  constructor(public rentalOfficeService: RentalOfficeService) { }

  ngOnInit() {
  }

  saveRentalOffice() {
    const rentalOffice = new RentalOffice(null, this.name, this.internetDomain, this.contactAddress, this.owner, this.logoType);
    this.rentalOfficeService.saveRentalOffice(rentalOffice)
    .subscribe(result => console.log('saved'));
  }

}
