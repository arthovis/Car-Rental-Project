import { ReturnsService } from './../returns.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Rental } from 'src/app/shared/model/rental';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faTrash, faSearchPlus } from '@fortawesome/free-solid-svg-icons';
import { Return } from 'src/app/shared/model/return';

@Component({
  selector: 'app-returns-overview',
  templateUrl: './returns-overview.component.html',
  styleUrls: ['./returns-overview.component.css']
})
export class ReturnsOverviewComponent implements OnInit {

  returns: Observable<Return[]>;

  displayedColumns: string[];
  trashIcon: IconDefinition = faTrash;
  searchIcon: IconDefinition = faSearchPlus;

  constructor(private returnService: ReturnsService) { }

  ngOnInit() {
    this.loadReturns();
    this.displayedColumns = ['id', 'dateOfReturn', 'employeeDto', 'actions'];
  }

  private loadReturns() {
    this.returns = this.returnService.getAllReturns();
  }

}
