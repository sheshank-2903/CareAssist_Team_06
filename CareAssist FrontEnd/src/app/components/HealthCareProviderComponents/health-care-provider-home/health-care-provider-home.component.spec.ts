import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthCareProviderHomeComponent } from './health-care-provider-home.component';

describe('HealthCareProviderHomeComponent', () => {
  let component: HealthCareProviderHomeComponent;
  let fixture: ComponentFixture<HealthCareProviderHomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HealthCareProviderHomeComponent]
    });
    fixture = TestBed.createComponent(HealthCareProviderHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
