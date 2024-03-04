import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthCareProviderComponent } from './health-care-provider.component';

describe('HealthCareProviderComponent', () => {
  let component: HealthCareProviderComponent;
  let fixture: ComponentFixture<HealthCareProviderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HealthCareProviderComponent]
    });
    fixture = TestBed.createComponent(HealthCareProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
