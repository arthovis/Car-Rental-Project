import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentalOfficeComponent } from './rental-office.component';

describe('RentalOfficeComponent', () => {
  let component: RentalOfficeComponent;
  let fixture: ComponentFixture<RentalOfficeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentalOfficeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentalOfficeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
