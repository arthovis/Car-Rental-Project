import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeesListComponent } from '../employees/employees-list/employees-list.component';
import { ReturnsOverviewComponent } from './returns-overview/returns-overview.component';
import { ReturnsDetailsComponent } from './returns-details/returns-details.component';
import { ReturnsUpdateComponent } from './returns-update/returns-update.component';
import { ReturnsComponent } from './returns.component';


const routes: Routes = [
  {
    path: 'returns',
    component: ReturnsComponent,
    children: [
      {
        path: '',
        component: ReturnsOverviewComponent
      },
    ]
  },
  {
    path: 'returns/:id/details',
    component: ReturnsDetailsComponent
  },
  {
    path: 'returns/:id/employees-list',
    component: EmployeesListComponent
  },
  {
    path: 'returns/:id/update',
    component: ReturnsUpdateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReturnsRoutingModule { }
