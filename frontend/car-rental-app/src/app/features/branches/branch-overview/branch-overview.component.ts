import { Component, OnInit } from '@angular/core';
import { Branch } from 'src/app/shared/model/branch';
import { IconDefinition, faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { Observable } from 'rxjs';
import { BranchesService } from '../branches.service';

@Component({
  selector: 'app-branch-overview',
  templateUrl: './branch-overview.component.html',
  styleUrls: ['./branch-overview.component.css']
})
export class BranchOverviewComponent implements OnInit {

  branches: Observable<Branch[]>;
  branchById: Observable<Branch>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(private branchService: BranchesService) { }

  ngOnInit() {
    this.loadBranches();
    this.displayedColumns = ['id', 'address', 'actions'];
  }

  private loadBranches() {
    this.branches = this.branchService.getAllBranches();
  }

  delete(id: number) {
    this.branchService.deleteBranch(id).subscribe(
      data => {
        this.loadBranches();
      },
      error => console.log(error));
  }

}
