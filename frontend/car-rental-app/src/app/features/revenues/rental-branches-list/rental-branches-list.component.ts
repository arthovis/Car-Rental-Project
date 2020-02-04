import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { Branch } from 'src/app/shared/model/branch';
import { BranchesService } from '../../branches/branches.service';
import { ActivatedRoute, Router } from '@angular/router';

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

  constructor(
    private branchService: BranchesService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.loadBranches();
    this.displayedColumns = ['id', 'address', 'actions'];
  }

  loadBranches() {
    this.branches = this.branchService.getAllBranches();
  }

  getIdFromRoute(): number {
    // (+) before transforma string in numar
    return +this.route.snapshot.paramMap.get('id');
  }

  selectBranch(id: number) {
    this.sendId.emit(id);
  }

}
