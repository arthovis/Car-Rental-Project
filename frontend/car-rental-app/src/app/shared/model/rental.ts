import { Employee } from './employee';
import { Branch } from './branch';

export class Rental {

    constructor( public id: number,
                 public rentalDate: Date,
                 public comments: string,
                 public employeeDto: Employee,
                 public branchDto: Branch
                ) { }
}
