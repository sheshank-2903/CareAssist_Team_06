import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminHealthCareProviderComponent } from './admin-health-care-provider.component';

describe('AdminHealthCareProviderComponent', () => {
  let component: AdminHealthCareProviderComponent;
  let fixture: ComponentFixture<AdminHealthCareProviderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminHealthCareProviderComponent]
    });
    fixture = TestBed.createComponent(AdminHealthCareProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
