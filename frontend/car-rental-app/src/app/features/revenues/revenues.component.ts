import { RevenuesService } from './revenues.service';
import { Revenue } from './../../shared/model/revenue';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-revenues',
  templateUrl: './revenues.component.html',
  styleUrls: ['./revenues.component.css']
})
export class RevenuesComponent implements OnInit {

  revenue = new Revenue(null, 0);

  constructor(private revenueService: RevenuesService) { }

  ngOnInit() {
  }

  receiveId(id: number ) {
    this.revenueService.getRevenue(id).subscribe(revenue => this.revenue = revenue);
  }

  clear() {
    this.revenue.totalRevenue = 0;
  }

}
