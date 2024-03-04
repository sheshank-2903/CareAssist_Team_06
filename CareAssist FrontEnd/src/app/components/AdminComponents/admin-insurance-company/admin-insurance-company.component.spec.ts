import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminInsuranceCompanyComponent } from './admin-insurance-company.component';

describe('AdminInsuranceCompanyComponent', () => {
  let component: AdminInsuranceCompanyComponent;
  let fixture: ComponentFixture<AdminInsuranceCompanyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminInsuranceCompanyComponent]
    });
    fixture = TestBed.createComponent(AdminInsuranceCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
