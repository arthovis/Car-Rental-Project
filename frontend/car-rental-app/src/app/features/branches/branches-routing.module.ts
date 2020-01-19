import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BranchOverviewComponent } from './branch-overview/branch-overview.component';
import { BranchesComponent } from './branches.component';
import { BranchNewComponent } from './branch-new/branch-new.component';
import { BranchUpdateComponent } from './branch-update/branch-update.component';


const routes: Routes = [
  {
    path: 'branches',
    component: BranchesComponent,
    children: [
      {
        path: '',
        component: BranchOverviewComponent
      },
    ]
  },
  {
    path: 'branches/new',
    component: BranchNewComponent
  },
  {
    path: 'branches/:id/update',
    component: BranchUpdateComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchesRoutingModule { }
