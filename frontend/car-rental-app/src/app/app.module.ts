import { MaterialModule } from './shared/material/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RentalOfficeComponent } from './features/rental-office/rental-office.component';
import { HomeComponent } from './features/home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SuccessSnackComponent } from './shared/components/success-snack/success-snack.component';
import { RentalOfficeOverviewComponent } from './features/rental-office-overview/rental-office-overview.component';
import { FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { BranchComponent } from './features/branch/branch.component';
import { RentalOfficeDetailsComponent } from './features/rental-office-details/rental-office-details.component';

@NgModule({
  entryComponents:
  [SuccessSnackComponent],
  declarations: [
    AppComponent,
    RentalOfficeComponent,
    HomeComponent,
    SuccessSnackComponent,
    RentalOfficeOverviewComponent,
    BranchComponent,
    RentalOfficeDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
