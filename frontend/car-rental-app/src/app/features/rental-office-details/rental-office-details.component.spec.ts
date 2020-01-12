import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentalOfficeDetailsComponent } from './rental-office-details.component';

describe('RentalOfficeDetailsComponent', () => {
  let component: RentalOfficeDetailsComponent;
  let fixture: ComponentFixture<RentalOfficeDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentalOfficeDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentalOfficeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
