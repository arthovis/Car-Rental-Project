import { EmployeeModule } from './features/employees/employee.module';
import { ReturnsModule } from './features/returns/returns.module';
import { RentalOfficesModule } from './features/rental-offices/rental-offices.module';
import { MaterialModule } from './shared/material/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './features/home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SuccessSnackComponent } from './shared/components/success-snack/success-snack.component';
import { FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { PageNotFoundComponent } from './features/page-not-found/page-not-found.component';
import { BranchesModule } from './features/branches/branches.module';
import { CarsModule } from './features/cars/cars.module';
import { RentalsModule } from './features/rentals/rentals.module';


@NgModule({
  entryComponents:
  [SuccessSnackComponent],
  declarations: [
    AppComponent,
    HomeComponent,
    PageNotFoundComponent,
    SuccessSnackComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    RentalOfficesModule,
    BranchesModule,
    CarsModule,
    RentalsModule,
    ReturnsModule,
    EmployeeModule,
    AppRoutingModule,
    MaterialModule,
    FontAwesomeModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
