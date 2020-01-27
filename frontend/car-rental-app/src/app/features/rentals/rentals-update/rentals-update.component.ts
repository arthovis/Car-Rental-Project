import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { Rental } from 'src/app/shared/model/rental';
import { RentalsService } from '../rentals.service';

@Component({
  selector: 'app-rentals-update',
  templateUrl: './rentals-update.component.html',
  styleUrls: ['./rentals-update.component.css']
})
export class RentalsUpdateComponent implements OnInit {

  rentalToUpdate = new Rental(null, null, null, null, null);

  constructor(private router: Router,
              private route: ActivatedRoute,
              private rentalService: RentalsService,
              private snackBar: MatSnackBar,
              ) { }

  ngOnInit() {
    this.rentalService.getRentalById(this.getIdFromRoute()).subscribe(
      result => this.rentalToUpdate = result
    );
  }

  changeRental() {
    this.rentalService.updateRental(this.getIdFromRoute(), this.rentalToUpdate)
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

  goToRentalDetails() {
    this.router.navigate(['/rentals', this.getIdFromRoute(), 'details']);
  }

}
