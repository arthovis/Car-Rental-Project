import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Branch } from 'src/app/shared/model/branch';
import { IconDefinition, faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { MatSnackBar } from '@angular/material';
import { BranchesService } from '../branches.service';
import { RentalOfficesService } from '../../rental-offices/rental-offices.service';

@Component({
  selector: 'app-branches-list',
  templateUrl: './branches-list.component.html',
  styleUrls: ['./branches-list.component.css']
})
export class BranchesListComponent implements OnInit {

  branches: Observable<Branch[]>;
  branchById: Branch;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(
    private branchService: BranchesService,
    private rentalOfficeService: RentalOfficesService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.loadBranches();
    this.displayedColumns = ['id', 'address', 'actions'];
  }

  loadBranches() {
    this.branches = this.branchService.getAllBranches();
  }

  addBranch(id: number): void {
    this.branchService.getBranchById(id)
      .subscribe(branch => {
        this.branchById = branch;
        this.rentalOfficeService.addBranch(this.getIdFromRoute(), this.branchById).subscribe();
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

  goToRentalOffices() {
    this.router.navigate(['/rental-offices', this.getIdFromRoute(), 'details']);
  }

}
