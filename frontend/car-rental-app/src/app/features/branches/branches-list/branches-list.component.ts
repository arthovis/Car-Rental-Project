import { PageEvent } from '@angular/material/paginator';
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

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  constructor(
    private branchService: BranchesService,
    private rentalOfficeService: RentalOfficesService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'address', 'actions'];
  }

  private loadFirstPage() {
    this.branches = this.branchService.getAllBranches(this.pageIndex, this.pageSize);
  }

  public getBranches(event: PageEvent) {
    this.branches = this.branchService.getAllBranches(event.pageIndex, event.pageSize);
  }

  addBranch(id: number): void {
    this.branchService.getBranchById(id)
      .subscribe(branch => {
        this.rentalOfficeService.addBranch(this.getIdFromRoute(), branch).subscribe();
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
