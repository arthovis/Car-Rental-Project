import { Branch } from 'src/app/shared/model/branch';
export class RentalOffice {

    constructor( public id: number,
                 public name: string,
                 public internetDomain: string,
                 public contactAddress: string,
                 public owner: string,
                 public logoType: string,
                 public branches: Branch[]) {
                }
}
