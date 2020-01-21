import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { BranchesService } from '../branches.service';
import { Branch } from 'src/app/shared/model/branch';
import { Router } from '@angular/router';

@Component({
  selector: 'app-branch-new',
  templateUrl: './branch-new.component.html',
  styleUrls: ['./branch-new.component.css']
})
export class BranchNewComponent implements OnInit {

  address: string;

  constructor(public branchService: BranchesService,
              private snackBar: MatSnackBar,
              private router: Router
              ) { }

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

  goToBranches() {
    this.router.navigate(['/branches']);
  }

}
