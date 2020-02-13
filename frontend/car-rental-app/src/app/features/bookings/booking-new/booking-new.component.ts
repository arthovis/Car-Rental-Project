import { Customer } from './../../../shared/model/customer';
import { Branch } from './../../../shared/model/branch';
import { CarsService } from './../../cars/cars.service';
import { BranchesService } from './../../branches/branches.service';
import { BookingsService } from './../bookings.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { Booking } from 'src/app/shared/model/booking';
import { Observable } from 'rxjs';
import { Car } from 'src/app/shared/model/car';
import { CustomerService } from '../../customer/customer.service';
import { Status } from 'src/app/shared/model/status';

@Component({
  selector: 'app-booking-new',
  templateUrl: './booking-new.component.html',
  styleUrls: ['./booking-new.component.css']
})
export class BookingNewComponent implements OnInit {

  customers: Customer[];
  branches: Branch[];
  cars: Car[];
  dateFrom: Date;
  dateTo: Date;
  rentalBranchDto: Branch;
  returnBranchDto: Branch;
  customerDto: Customer;
  carDto: Car;

  constructor(private bookingsService: BookingsService,
              private customerService: CustomerService,
              private branchService: BranchesService,
              private carService: CarsService,
              private snackBar: MatSnackBar,
              private router: Router
              ) { }

  ngOnInit() {
    this.getCustomers();
    this.getBranches();
    this.getCars();
  }

  createBooking() {
    // tslint:disable-next-line: max-line-length
    const booking = new Booking(null, null, this.dateFrom, this.dateTo, this.rentalBranchDto, this.returnBranchDto, this.customerDto, this.carDto, null, null, null, null);
    this.bookingsService.saveBooking(booking)
      .subscribe(result => {
        this.snackBar.openFromComponent(SuccessSnackComponent, {
          duration: 2000,
          verticalPosition: 'top'
        });
      });
  }

  getCustomers() {
    this.customerService.getCustomers().subscribe((customers: Customer[]) => this.customers = customers);
  }

  getBranches() {
    this.branchService.getBranches().subscribe((branches: Branch[]) => this.branches = branches);
  }

  getCars() {
    this.carService.getCars().subscribe((cars: Car[]) => this.cars = cars.filter(car => {
      return car.status === Status.available;
    }));
  }

  goToBookings() {
    this.router.navigate(['/bookings']);
  }

  displayFn1(customer?: Customer): string | undefined {
    return customer ? customer.firstName + ' ' + customer.lastName : undefined;
  }

  displayFn2(branch?: Branch): string | undefined {
    return branch ? branch.address : undefined;
  }

  displayFn3(car?: Car): string | undefined {
    return car ? car.model : undefined;
  }

  dateFilter1 = (d: Date): boolean => {
    return d > new Date();
  }

  dateFilter2 = (d: Date): boolean => {
    return d > this.dateFrom;
  }

}
