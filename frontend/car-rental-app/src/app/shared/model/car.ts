import { Status } from './status';

export class Car {

    constructor( public id: number,
                 public make: string,
                 public model: string,
                 public bodyType: string,
                 public yearOfProduction: number,
                 public color: string,
                 public mileage: number,
                 public status: Status,
                 public amount: number) {
                }
}
