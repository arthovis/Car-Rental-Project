import { BranchesService } from '../../branches/branches.service';
import { Branch } from 'src/app/shared/model/branch';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/shared/model/employee';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { map, flatMap } from 'rxjs/operators';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { Return } from 'src/app/shared/model/return';
import { ReturnsService } from '../returns.service';

@Component({
  selector: 'app-returns-details',
  templateUrl: './returns-details.component.html',
  styleUrls: ['./returns-details.component.css']
})
export class ReturnsDetailsComponent implements OnInit {

  returns: Observable<Return[]>;
  return: Return;
  employees: Employee[];
  view1: false;

  displayedColumns: string[];

  constructor(private returnService: ReturnsService,
              private branchService: BranchesService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private snackBar: MatSnackBar
            ) { }

  ngOnInit() {
    this.loadReturnDetails();
    this.displayedColumns = ['id', 'dateOfReturn', 'comments', 'additionalPayment', 'employeeDto', 'branchDto', 'actions'];
  }

  loadReturnDetails() {
    this.returns = this.activatedRoute.params.pipe(
      // tslint:disable-next-line: no-string-literal
      map(params => params['id']),
      flatMap(id => this.returnService.getReturnById(id)),
      map(cro => [cro]));

    this.returns.subscribe(returns => {
      this.return = returns[0];
    });
  }

  goBack() {
    this.router.navigate(['/returns']);
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.activatedRoute.snapshot.paramMap.get('id');
  }

  listBranchEmployees(id: number) {
    this.branchService.getBranchById(id).subscribe(branch =>
      this.employees = branch.employeeList
    );
  }

  addEmployee(employee: Employee) {
    this.returnService.addEmployee(this.getIdFromRoute(), employee).subscribe();
    this.snackBar.openFromComponent(SuccessSnackComponent, {
      duration: 2000,
      verticalPosition: 'top'
    });
    this.loadReturnDetails();
  }

  deleteEmployee() {
    this.return.employeeDto = null;
    this.returnService.deleteEmployee(this.getIdFromRoute(), this.return).subscribe();
    this.snackBar.openFromComponent(SuccessSnackComponent, {
      duration: 2000,
      verticalPosition: 'top'
    });
    this.loadReturnDetails();
  }

}
