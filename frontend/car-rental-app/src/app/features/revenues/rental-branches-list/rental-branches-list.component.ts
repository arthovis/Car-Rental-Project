import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { Branch } from 'src/app/shared/model/branch';
import { BranchesService } from '../../branches/branches.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-rental-branches-list',
  templateUrl: './rental-branches-list.component.html',
  styleUrls: ['./rental-branches-list.component.css']
})
export class RentalBranchesListComponent implements OnInit {

  @Output() sendId = new EventEmitter<number>();

  branches: Observable<Branch[]>;
  branchById: Branch;

  displayedColumns: string[];

  length = 100;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  constructor(
    private branchService: BranchesService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.loadFirstPage();
    this.displayedColumns = ['id', 'address', 'actions'];
  }

  loadFirstPage() {
    this.branches = this.branchService.getAllBranches(this.pageIndex, this.pageSize);
  }

  public getBranches(event: PageEvent) {
    this.branches = this.branchService.getAllBranches(event.pageIndex, event.pageSize);
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.route.snapshot.paramMap.get('id');
  }

  selectBranch(id: number) {
    this.sendId.emit(id);
  }

}
