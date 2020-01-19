import { Component, OnInit } from '@angular/core';
import { RentalOfficesService } from '../rental-offices.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-rental-office-update',
  templateUrl: './rental-office-update.component.html',
  styleUrls: ['./rental-office-update.component.css']
})
export class RentalOfficeUpdateComponent implements OnInit {

  rentalOfficeToUpdate = new RentalOffice(null, null, null, null, null, null, null);

  constructor(private rentalOfficeService: RentalOfficesService,
              private snackBar: MatSnackBar,
              private route: ActivatedRoute,
              private router: Router,
  ) { }

  ngOnInit() {
    this.rentalOfficeService.getCarRentalOfficeById(this.getIdFromRoute()).subscribe(
      result => this.rentalOfficeToUpdate = result
    );
  }

  updateRentalOffice() {
    this.rentalOfficeService.updateRentalOffice(this.getIdFromRoute(), this.rentalOfficeToUpdate)
      .subscribe(result => {
        this.snackBar.openFromComponent(SuccessSnackComponent, {
          duration: 2000,
          verticalPosition: 'top'
        });
      });
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.route.snapshot.paramMap.get('id');
  }

  goToRentalOffices() {
    this.router.navigate(['/rental-offices', this.getIdFromRoute(), 'details']);
  }

}
