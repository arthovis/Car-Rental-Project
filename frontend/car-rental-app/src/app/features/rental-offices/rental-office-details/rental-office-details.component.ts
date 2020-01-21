import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { map, flatMap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { RentalOfficesService } from '../rental-offices.service';
import { RentalOffice } from 'src/app/shared/model/rentalOffice';
import { Branch } from 'src/app/shared/model/branch';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-rental-office-details',
  templateUrl: './rental-office-details.component.html',
  styleUrls: ['./rental-office-details.component.css']
})
export class RentalOfficeDetailsComponent implements OnInit {

  rentalOffices: Observable<RentalOffice[]>;
  office: RentalOffice;
  branches: Branch[];
  view: boolean;

  displayedColumns: string[];

  constructor(private rentalOfficeService: RentalOfficesService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar
              ) { }

  ngOnInit() {
    this.loadRentalOfficeDetails();
    this.displayedColumns = ['id', 'name', 'internetDomain', 'contactAddress', 'owner', 'logoType', 'branches', 'actions'];
  }

  loadRentalOfficeDetails() {
    this.rentalOffices = this.activatedRoute.params.pipe(
      // tslint:disable-next-line: no-string-literal
      map(params => params['id']),
      flatMap(id => this.rentalOfficeService.getCarRentalOfficeById(id)),
      map(cro => [cro]));

    this.rentalOffices.subscribe(offices => {
      this.office = offices[0];
      this.branches = this.office.branches;
    });
  }

  goBack() {
    this.router.navigate(['/rental-offices']);
  }

  deleteBranch(branch: Branch): void {
    this.rentalOfficeService.deleteBranch(this.getIdFromRoute(), branch)
    .subscribe(data => {
      this.loadRentalOfficeDetails();
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

}
