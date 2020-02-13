import { RentalsService } from './../rentals.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Rental } from 'src/app/shared/model/rental';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-rentals-overview',
  templateUrl: './rentals-overview.component.html',
  styleUrls: ['./rentals-overview.component.css']
})
export class RentalsOverviewComponent implements OnInit {

  rentals: Observable<Rental[]>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  constructor(private rentalService: RentalsService) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'rentalDate', 'employeeDto', 'actions'];
  }

  private loadFirstPage() {
    this.rentals = this.rentalService.getAllRentals(this.pageIndex, this.pageSize);
  }

  public getRentals(event: PageEvent) {
    this.rentals = this.rentalService.getAllRentals(event.pageIndex, event.pageSize);
  }

}
