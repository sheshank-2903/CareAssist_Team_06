import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceCompanyComponent } from './insurance-company.component';

describe('InsuranceCompanyComponent', () => {
  let component: InsuranceCompanyComponent;
  let fixture: ComponentFixture<InsuranceCompanyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InsuranceCompanyComponent]
    });
    fixture = TestBed.createComponent(InsuranceCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});