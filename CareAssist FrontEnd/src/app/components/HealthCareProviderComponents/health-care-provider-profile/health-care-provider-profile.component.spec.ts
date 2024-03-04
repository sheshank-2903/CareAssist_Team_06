import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthCareProviderProfileComponent } from './health-care-provider-profile.component';

describe('HealthCareProviderProfileComponent', () => {
  let component: HealthCareProviderProfileComponent;
  let fixture: ComponentFixture<HealthCareProviderProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HealthCareProviderProfileComponent]
    });
    fixture = TestBed.createComponent(HealthCareProviderProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
