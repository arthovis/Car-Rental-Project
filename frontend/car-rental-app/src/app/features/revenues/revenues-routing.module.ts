import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RevenuesComponent } from './revenues.component';


const routes: Routes = [
  {
    path: 'revenues',
    component: RevenuesComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RevenuesRoutingModule { }
