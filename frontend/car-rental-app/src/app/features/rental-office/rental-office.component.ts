import { Component, OnInit } from '@angular/core';

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

  constructor() { }

  ngOnInit() {
  }

  saveRentalOffice(){
    alert(this.contactAddress);
  }

}
