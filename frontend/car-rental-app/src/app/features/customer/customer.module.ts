import { CustomerOverviewComponent } from './customer-overview/customer-overview.component';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';
import { CustomerComponent } from './customer.component';
import { NgModule } from '@angular/core';
import { CustomerNewComponent } from './customer-new/customer-new.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CustomerRoutingModule } from './customer-routing.module';

@NgModule({
    declarations: [
        CustomerComponent,
        CustomerNewComponent,
        CustomerDetailsComponent,
        CustomerUpdateComponent,
        CustomerOverviewComponent,
        CustomerNewComponent,
        CustomerDetailsComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MaterialModule,
        FontAwesomeModule,
        CustomerRoutingModule
    ],
})

export class CustomerModule {}
