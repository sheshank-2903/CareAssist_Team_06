import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientClaimsComponent } from './patient-claims.component';

describe('PatientClaimsComponent', () => {
  let component: PatientClaimsComponent;
  let fixture: ComponentFixture<PatientClaimsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PatientClaimsComponent]
    });
    fixture = TestBed.createComponent(PatientClaimsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
