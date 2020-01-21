import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BranchesListComponent } from './branches-list.component';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material/material.module';

@NgModule({
  declarations: [
    BranchesListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
  ],
  exports: [
    BranchesListComponent
  ]
})
export class BranchesListModule { }
