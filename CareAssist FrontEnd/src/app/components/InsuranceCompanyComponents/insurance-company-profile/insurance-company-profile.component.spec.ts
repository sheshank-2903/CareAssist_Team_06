import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceCompanyProfileComponent } from './insurance-company-profile.component';

describe('InsuranceCompanyProfileComponent', () => {
  let component: InsuranceCompanyProfileComponent;
  let fixture: ComponentFixture<InsuranceCompanyProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InsuranceCompanyProfileComponent]
    });
    fixture = TestBed.createComponent(InsuranceCompanyProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
