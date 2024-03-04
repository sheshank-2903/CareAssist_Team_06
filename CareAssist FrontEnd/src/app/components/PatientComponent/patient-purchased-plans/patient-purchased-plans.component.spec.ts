import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPurchasedPlansComponent } from './patient-purchased-plans.component';

describe('PatientPurchasedPlansComponent', () => {
  let component: PatientPurchasedPlansComponent;
  let fixture: ComponentFixture<PatientPurchasedPlansComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PatientPurchasedPlansComponent]
    });
    fixture = TestBed.createComponent(PatientPurchasedPlansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
