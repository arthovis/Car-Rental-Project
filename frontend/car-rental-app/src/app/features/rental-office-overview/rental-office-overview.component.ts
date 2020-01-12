import { RentalOfficeService } from './../rental-office/rental-office.service';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';

@Component({
  selector: 'app-rental-office-overview',
  templateUrl: './rental-office-overview.component.html',
  styleUrls: ['./rental-office-overview.component.css']
})
export class RentalOfficeOverviewComponent implements OnInit {

  rentalOffices: Observable<RentalOffice[]>;

  displayedColumns: string[];

  constructor(private rentalOfficeService: RentalOfficeService) {
  }

  ngOnInit() {
    this.rentalOffices = this.rentalOfficeService.getAllRentalOffices();
    this.displayedColumns = ['id', 'name', 'internetDomain', 'contactAddress', 'owner', 'logoType'];
  }

}
