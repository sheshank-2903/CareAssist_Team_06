import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceCompanyClaimsComponent } from './insurance-company-claims.component';

describe('InsuranceCompanyClaimsComponent', () => {
  let component: InsuranceCompanyClaimsComponent;
  let fixture: ComponentFixture<InsuranceCompanyClaimsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InsuranceCompanyClaimsComponent]
    });
    fixture = TestBed.createComponent(InsuranceCompanyClaimsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
