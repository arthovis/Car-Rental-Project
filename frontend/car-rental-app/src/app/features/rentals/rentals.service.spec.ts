import { TestBed } from '@angular/core/testing';

import { RentalsService } from './rentals.service';

describe('RentalsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RentalsService = TestBed.get(RentalsService);
    expect(service).toBeTruthy();
  });
});
