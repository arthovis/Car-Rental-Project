import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { BranchesRoutingModule } from './branches-routing.module';
import { BranchesComponent } from './branches.component';
import { BranchNewComponent } from './branch-new/branch-new.component';
import { BranchOverviewComponent } from './branch-overview/branch-overview.component';
import { BranchUpdateComponent } from './branch-update/branch-update.component';

@NgModule({
  declarations: [
    BranchesComponent,
    BranchNewComponent,
    BranchOverviewComponent,
    BranchUpdateComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    FontAwesomeModule,
    BranchesRoutingModule
  ],
})
export class BranchesModule { }
