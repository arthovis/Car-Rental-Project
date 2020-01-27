import { RentalsService } from './../rentals.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Rental } from 'src/app/shared/model/rental';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';

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

  constructor(private rentalService: RentalsService) { }

  ngOnInit() {
    this.loadRentals();
    this.displayedColumns = ['id', 'rentalDate', 'employeeDto', 'actions'];
  }

  private loadRentals() {
    this.rentals = this.rentalService.getAllRentals();
  }

}
