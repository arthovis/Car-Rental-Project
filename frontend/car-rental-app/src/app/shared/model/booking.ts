import { Branch } from './branch';
import { BookingStatus } from './booking-status';
import { Return } from './return';
import { Rental } from './rental';
import { Car } from './car';
import { Customer } from './customer';

export class Booking {

    constructor( public id: number,
                 public dateOfBooking: Date,
                 public dateFrom: Date,
                 public dateTo: Date,
                 public rentalBranchDto: Branch,
                 public returnBranchDto: Branch,
                 public customerDto: Customer,
                 public carDto: Car,
                 public RentalDto: Rental,
                 public CarReturnDto: Return,
                 public amount: number,
                 public bookingStatus: BookingStatus
                ) { }
}
