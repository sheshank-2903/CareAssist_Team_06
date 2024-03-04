import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminClaimsComponent } from './admin-claims.component';

describe('AdminClaimsComponent', () => {
  let component: AdminClaimsComponent;
  let fixture: ComponentFixture<AdminClaimsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminClaimsComponent]
    });
    fixture = TestBed.createComponent(AdminClaimsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
