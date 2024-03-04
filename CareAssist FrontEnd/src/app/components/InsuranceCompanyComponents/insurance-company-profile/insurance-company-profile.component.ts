import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { InsuranceCompany } from 'src/app/model/InsuranceCompany';
import { InsuranceCompanyService } from 'src/app/services/InsuranceCompanyServices/insurance-company.service';

@Component({
  selector: 'app-insurance-company-profile',
  templateUrl: './insurance-company-profile.component.html',
  styleUrls: ['./insurance-company-profile.component.css']
})
export class InsuranceCompanyProfileComponent {
  editable: boolean = true;

  updateForm !: FormGroup;
  insuranceCompany: InsuranceCompany = {
    "insuranceCompanyId": 0,
    "insuranceCompanyDescription": "",
    "companyName": "",
    "companyContactNumber": "",
    "email": "",
    "password": ""
  }



  constructor(private router: Router, private formBuilder: FormBuilder, private insuranceCompanyService: InsuranceCompanyService, private cookieService: CookieService) {

    this.insuranceCompanyService.getInsuranceCompanyById(JSON.parse(this.cookieService.get('userId')).userId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(insuranceCompany => {
        this.insuranceCompany = insuranceCompany;
        this.updateForm = this.formBuilder.group({
          insuranceCompanyId: [this.insuranceCompany.insuranceCompanyId, []],
          insuranceCompanyDescription: [this.insuranceCompany.insuranceCompanyDescription, [Validators.required, Validators.minLength(20)]],
          companyName: [this.insuranceCompany.companyName, [Validators.required, Validators.pattern('^[a-zA-Z ]{5,20}$')]],
          companyContactNumber: [this.insuranceCompany.companyContactNumber, [Validators.required, Validators.pattern('\\d{10}')]],
          email: [this.insuranceCompany.email, [Validators.required, Validators.email]],
          password: ['', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$')]],
          confirm_password: ['', Validators.required]
        }, { validator: this.passwordMatchValidator });
      },error=>{alert("Please try Again! Error Occured");})

  }

  get f() {
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
    this.insuranceCompany.companyName = this.updateForm.value.companyName;
    this.insuranceCompany.insuranceCompanyDescription = this.updateForm.value.insuranceCompanyDescription;
    this.insuranceCompany.companyContactNumber = this.updateForm.value.companyContactNumber;
    this.insuranceCompany.email = this.updateForm.value.email;
    this.insuranceCompany.password = this.updateForm.value.password;
    this.insuranceCompanyService.updateInsuranceCompany(this.insuranceCompany, JSON.parse(this.cookieService.get('userId')).userToken).
      subscribe(updatedData => {
        alert('Details updated successfully');
        location.reload();
      },error=>{alert("unable to update details");})
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
