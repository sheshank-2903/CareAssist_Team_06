import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthCareProviderPatientComponent } from './health-care-provider-patient.component';

describe('HealthCareProviderPatientComponent', () => {
  let component: HealthCareProviderPatientComponent;
  let fixture: ComponentFixture<HealthCareProviderPatientComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HealthCareProviderPatientComponent]
    });
    fixture = TestBed.createComponent(HealthCareProviderPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
