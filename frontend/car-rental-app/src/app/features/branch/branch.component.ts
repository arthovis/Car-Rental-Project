import { Branch } from './../../shared/model/branch';
import { BranchService } from './branch.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.css']
})
export class BranchComponent implements OnInit {

  address: string;

  constructor(public branchService: BranchService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  createBranch() {
    const branch = new Branch(null, this.address);
    this.branchService.saveBranch(branch)
      .subscribe(result => {
        this.snackBar.openFromComponent(SuccessSnackComponent, {
          duration: 2000,
          verticalPosition: 'top'
        });
      });
  }

}
