import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceCompanyPlansComponent } from './insurance-company-plans.component';

describe('InsuranceCompanyPlansComponent', () => {
  let component: InsuranceCompanyPlansComponent;
  let fixture: ComponentFixture<InsuranceCompanyPlansComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InsuranceCompanyPlansComponent]
    });
    fixture = TestBed.createComponent(InsuranceCompanyPlansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
