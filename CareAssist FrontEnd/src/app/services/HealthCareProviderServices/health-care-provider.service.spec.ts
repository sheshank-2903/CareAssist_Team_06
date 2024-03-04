import { TestBed } from '@angular/core/testing';

import { HealthCareProviderService } from './health-care-provider.service';

describe('HealthCareProviderService', () => {
  let service: HealthCareProviderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HealthCareProviderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
