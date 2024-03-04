import { TestBed } from '@angular/core/testing';

import { JwtServiceService } from '../LoginServices/jwt-service.service';

describe('JwtServiceService', () => {
  let service: JwtServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JwtServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
