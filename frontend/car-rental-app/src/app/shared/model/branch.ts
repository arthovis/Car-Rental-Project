import { Employee } from './employee';
import { Car } from './car';
export class Branch {

    constructor(public id: number,
                public address: string,
                public employeeList: Employee[],
                public availableCarsList: Car[]
                ) { }
}
