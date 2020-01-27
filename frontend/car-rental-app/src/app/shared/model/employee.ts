import { Branch } from './branch';
import { JobPosition } from './job-position';

export class Employee {

    constructor(public id: number,
                public nameAndSurname: string,
                public jobPosition: JobPosition,
                public branchDto: Branch) {
    }
}
