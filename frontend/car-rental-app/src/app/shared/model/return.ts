import { Employee } from './employee';
import { Branch } from './branch';

export class Return {

    constructor( public id: number,
                 public dateOfReturn: Date,
                 public comments: string,
                 public employeeDto: Employee,
                 public branchDto: Branch,
                 public additionalPayment: number
                ) { }
}
