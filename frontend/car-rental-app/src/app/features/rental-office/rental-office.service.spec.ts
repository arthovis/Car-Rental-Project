import { TestBed } from '@angular/core/testing';

import { RentalOfficeService } from './rental-office.service';

describe('RentalOfficeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RentalOfficeService = TestBed.get(RentalOfficeService);
    expect(service).toBeTruthy();
  });
});
