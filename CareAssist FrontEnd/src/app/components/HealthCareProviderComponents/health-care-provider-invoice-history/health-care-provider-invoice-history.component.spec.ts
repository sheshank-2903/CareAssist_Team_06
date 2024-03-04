import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthCareProviderInvoiceHistoryComponent } from './health-care-provider-invoice-history.component';

describe('HealthCareProviderInvoiceHistoryComponent', () => {
  let component: HealthCareProviderInvoiceHistoryComponent;
  let fixture: ComponentFixture<HealthCareProviderInvoiceHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HealthCareProviderInvoiceHistoryComponent]
    });
    fixture = TestBed.createComponent(HealthCareProviderInvoiceHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
