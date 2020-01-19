import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';
import { faTrash, IconDefinition, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { RentalOfficesService } from '../rental-offices.service';

@Component({
  selector: 'app-rental-office-overview',
  templateUrl: './rental-office-overview.component.html',
  styleUrls: ['./rental-office-overview.component.css']
})
export class RentalOfficeOverviewComponent implements OnInit {

  rentalOffices: Observable<RentalOffice[]>;
  rentalOfficeById: Observable<RentalOffice>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(private rentalOfficeService: RentalOfficesService) {
  }

  ngOnInit() {
    this.loadRentalOffices();
    this.displayedColumns = ['id', 'name', 'actions'];
  }

  private loadRentalOffices() {
    this.rentalOffices = this.rentalOfficeService.getAllRentalOffices();
  }

  delete(id: number) {
    this.rentalOfficeService.deleteRentalOffice(id).subscribe(
      data => {
        this.loadRentalOffices();
      },
      error => console.log(error));
  }

}
