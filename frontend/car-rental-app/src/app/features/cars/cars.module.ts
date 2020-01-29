import { CarRoutingRoutingModule } from './car-routing-routing.module';
import { CarDetailsComponent } from './car-details/car-details.component';
import { CarUpdateComponent } from './car-update/car-update.component';
import { CarOverviewComponent } from './car-overview/car-overview.component';
import { CarNewComponent } from './car-new/car-new.component';
import { NgModule } from '@angular/core';
import { CarsComponent } from './cars.component';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@NgModule({
    declarations: [
        CarsComponent,
        CarNewComponent,
        CarOverviewComponent,
        CarUpdateComponent,
        CarDetailsComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        MaterialModule,
        FontAwesomeModule,
        CarRoutingRoutingModule
    ],
})
export class CarsModule { }
