import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Admin } from 'src/app/model/Admin';
import { AdminService } from 'src/app/services/AdminServices/admin.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent {
  editable: boolean = true;

  updateForm !: FormGroup;

  admin: Admin = {
    "adminId": 0,
    "adminName": "",
    "email": "",
    "password": "",
  }

  constructor(private formBuilder: FormBuilder, private cookieService: CookieService, private adminservice: AdminService) {
    this.adminservice.getAdminById(JSON.parse(this.cookieService.get('userId')).userId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(
        (admin) => {
          this.admin = admin;
          this.updateForm = this.formBuilder.group({
            adminId: [this.admin.adminId, []],
            adminName: [this.admin.adminName, [Validators.required, Validators.pattern('^[a-zA-Z ]{3,20}$')]],
            email: [this.admin.email, [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$')]],
            confirm_password: ['', Validators.required]
          }, { validator: this.passwordMatchValidator });
        }
      )
  }

  get getAdminForm() {
    return this.updateForm.controls;
  }

  toggleEditable() {
    this.editable = !this.editable;
    if (this.editable) {
      location.reload();
    }
  }

  onSubmit() {
    if (this.updateForm.invalid) {
      return;
    }
    this.admin.adminName = this.updateForm.value.adminName;
    this.admin.email = this.updateForm.value.email;
    this.admin.password = this.updateForm.value.password;
    this.adminservice.updateAdmin(this.admin, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(data => {
        alert('Profile updated successfully');
        location.reload();
      }, error => alert("Failed to update Profile"))
  }


  passwordMatchValidator(control: AbstractControl) {
    const password = control.get('password')?.value;
    const confirm_password = control.get('confirm_password')?.value;
    if (password !== confirm_password) {
      control.get('confirm_password')?.setErrors({ passwordMismatch: true });
      return { passwordMismatch: true };
    } else {
      return null;
    }
  }
}
