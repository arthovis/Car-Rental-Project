import { Branch } from './../../../shared/model/branch';
import { BranchesService } from './../branches.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';

@Component({
  selector: 'app-branch-update',
  templateUrl: './branch-update.component.html',
  styleUrls: ['./branch-update.component.css']
})
export class BranchUpdateComponent implements OnInit {

  branchToUpdate = new Branch(null, null, null, null);

  constructor(private router: Router,
              private route: ActivatedRoute,
              private branchService: BranchesService,
              private snackBar: MatSnackBar,
              ) { }

  ngOnInit() {
    this.branchService.getBranchById(this.getIdFromRoute()).subscribe(
      result => this.branchToUpdate = result
    );
  }

  changeBranch() {
    this.branchService.updateBranch(this.getIdFromRoute(), this.branchToUpdate)
      .subscribe(result => {
        this.snackBar.openFromComponent(SuccessSnackComponent, {
          duration: 2000,
          verticalPosition: 'top'
        });
      });
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.route.snapshot.paramMap.get('id');
  }

  goToBranchDetails() {
    this.router.navigate(['/branches', this.getIdFromRoute(), 'details']);
  }

}
