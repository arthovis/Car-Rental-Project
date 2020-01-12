import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentalOfficeOverviewComponent } from './rental-office-overview.component';

describe('RentalOfficeOverviewComponent', () => {
  let component: RentalOfficeOverviewComponent;
  let fixture: ComponentFixture<RentalOfficeOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentalOfficeOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentalOfficeOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
