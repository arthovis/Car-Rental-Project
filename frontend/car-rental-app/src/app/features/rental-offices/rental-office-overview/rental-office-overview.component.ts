import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';
import { faTrash, IconDefinition, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { RentalOfficesService } from '../rental-offices.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-rental-office-overview',
  templateUrl: './rental-office-overview.component.html',
  styleUrls: ['./rental-office-overview.component.css']
})
export class RentalOfficeOverviewComponent implements OnInit {

  rentalOffices: Observable<RentalOffice[]>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  pageEvent: PageEvent;

  constructor(private rentalOfficeService: RentalOfficesService) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'name', 'actions'];
  }

  private loadFirstPage() {
    this.rentalOffices = this.rentalOfficeService.getAllRentalOffices(this.pageIndex, this.pageSize);
  }

  delete(id: number) {
    this.rentalOfficeService.deleteRentalOffice(id).subscribe(
      data => {
        this.getRentalOffices(this.pageEvent);
      },
      error => console.log(error));
  }

  public getRentalOffices(event: PageEvent) {
    this.pageEvent = event;
    this.rentalOffices = this.rentalOfficeService.getAllRentalOffices(event.pageIndex, event.pageSize);
  }

}
