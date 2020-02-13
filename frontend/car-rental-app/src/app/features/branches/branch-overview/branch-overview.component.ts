import { PageEvent } from '@angular/material/paginator';
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

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  pageEvent: PageEvent;

  constructor(private branchService: BranchesService) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'address', 'actions'];
  }

  private loadFirstPage() {
    this.branches = this.branchService.getAllBranches(this.pageIndex, this.pageSize);
  }

  delete(id: number) {
    this.branchService.deleteBranch(id).subscribe(
      data => {
        this.getBranches(this.pageEvent);
      },
      error => console.log(error));
  }

  public getBranches(event: PageEvent) {
    this.pageEvent = event;
    this.branches = this.branchService.getAllBranches(event.pageIndex, event.pageSize);
  }

}
