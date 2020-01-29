import { Branch } from './branch';
import { JobPosition } from './job-position';

export class Customer {

    constructor(public id: number,
                public firstName: string,
                public lastName: string,
                public email: string,
                public address: string) {
    }
}
