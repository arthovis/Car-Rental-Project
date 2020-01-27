import { ReturnsService } from './../returns.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessSnackComponent } from 'src/app/shared/components/success-snack/success-snack.component';
import { Return } from 'src/app/shared/model/return';

@Component({
  selector: 'app-returns-update',
  templateUrl: './returns-update.component.html',
  styleUrls: ['./returns-update.component.css']
})
export class ReturnsUpdateComponent implements OnInit {

  returnToUpdate = new Return(null, null, null, null, null, null);

  constructor(private router: Router,
              private route: ActivatedRoute,
              private returnService: ReturnsService,
              private snackBar: MatSnackBar,
              ) { }

  ngOnInit() {
    this.returnService.getReturnById(this.getIdFromRoute()).subscribe(
      result => this.returnToUpdate = result
    );
  }

  changeReturn() {
    this.returnService.updateReturn(this.getIdFromRoute(), this.returnToUpdate)
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

  goToReturnDetails() {
    this.router.navigate(['/returns', this.getIdFromRoute(), 'details']);
  }

}
