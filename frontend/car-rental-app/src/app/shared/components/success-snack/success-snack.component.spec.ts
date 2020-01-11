import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessSnackComponent } from './success-snack.component';

describe('SuccessSnackComponent', () => {
  let component: SuccessSnackComponent;
  let fixture: ComponentFixture<SuccessSnackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessSnackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessSnackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
