import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientRequestInvoiceComponent } from './patient-request-invoice.component';

describe('PatientRequestInvoiceComponent', () => {
  let component: PatientRequestInvoiceComponent;
  let fixture: ComponentFixture<PatientRequestInvoiceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PatientRequestInvoiceComponent]
    });
    fixture = TestBed.createComponent(PatientRequestInvoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
