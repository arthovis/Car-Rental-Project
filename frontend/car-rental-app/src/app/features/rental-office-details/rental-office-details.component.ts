import { async } from '@angular/core/testing';
import { Branch } from 'src/app/shared/model/branch';
import { Component, OnInit } from '@angular/core';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';
import { Observable } from 'rxjs';
import { map, flatMap } from 'rxjs/operators';
import { RentalOfficeService } from '../rental-office/rental-office.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-rental-office-details',
  templateUrl: './rental-office-details.component.html',
  styleUrls: ['./rental-office-details.component.css']
})
export class RentalOfficeDetailsComponent implements OnInit {

  rentalOffices: Observable<RentalOffice[]>;
  offices: RentalOffice[];
  office: RentalOffice;
  branches: Branch[];
  view: false;

  displayedColumns: string[];

  constructor(private rentalOfficeService: RentalOfficeService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.rentalOffices = this.activatedRoute.params.pipe(
      // tslint:disable-next-line: no-string-literal
      map(params => params['id']),
      flatMap(id => this.rentalOfficeService.getCarRentalOfficeById(id)),
      map(cro => [cro])
    );

    this.rentalOffices.subscribe(offices => {
      this.offices = offices;
      this.office = this.offices[0];
    });

    this.displayedColumns = ['id', 'name', 'internetDomain', 'contactAddress', 'owner', 'logoType', 'branches', 'actions'];
  }

  listsBranches() {
    this.branches = this.office.branches;
  }

}
