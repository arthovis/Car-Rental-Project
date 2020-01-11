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

@NgModule({
  entryComponents:
  [SuccessSnackComponent],
  declarations: [
    AppComponent,
    RentalOfficeComponent,
    HomeComponent,
    SuccessSnackComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
