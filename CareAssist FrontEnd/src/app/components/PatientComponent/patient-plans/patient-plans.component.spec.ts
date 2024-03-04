import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPlansComponent } from './patient-plans.component';

describe('PatientPlansComponent', () => {
  let component: PatientPlansComponent;
  let fixture: ComponentFixture<PatientPlansComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PatientPlansComponent]
    });
    fixture = TestBed.createComponent(PatientPlansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
