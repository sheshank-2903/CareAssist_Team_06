import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { HealthCareProvider } from 'src/app/model/HealthCareProvider';
import { HealthCareProviderService } from 'src/app/services/HealthCareProviderServices/health-care-provider.service';

@Component({
  selector: 'app-health-care-provider-profile',
  templateUrl: './health-care-provider-profile.component.html',
  styleUrls: ['./health-care-provider-profile.component.css']
})
export class HealthCareProviderProfileComponent {
  editable: boolean = true;

  updateForm !: FormGroup;

  healthCareProvider: HealthCareProvider = {
    healthCareProviderId: 0,
    healthCareProviderName: '',
    providerGender: '',
    address: '',
    email: '',
    password: ''
  }


  constructor(private formBuilder: FormBuilder, private healthCareProviderService: HealthCareProviderService, private cookieService: CookieService) {
    this.healthCareProviderService.getHealthCareProviderById(JSON.parse(this.cookieService.get('userId')).userToken, JSON.parse(this.cookieService.get('userId')).userId)
      .subscribe(data => {
        this.healthCareProvider = data;
        this.updateForm = this.formBuilder.group({
          healthCareProviderId: [this.healthCareProvider.healthCareProviderId],
          healthCareProviderName: [this.healthCareProvider.healthCareProviderName, [Validators.required, Validators.pattern('^[a-zA-Z ]{3,20}$')]],
          address: [this.healthCareProvider.address, [Validators.required]],
          email: [this.healthCareProvider.email, [Validators.required, Validators.email]],
          providerGender: [this.healthCareProvider.providerGender],
          password: ['', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$')]],
          confirm_password: ['', Validators.required]
        }, { validator: this.passwordMatchValidator });
      },error=>{alert("Please try Again! Error Occured");}
    )
  }

  get getHealthCareProvider() {
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
    this.healthCareProvider.healthCareProviderName = this.updateForm.value.healthCareProviderName;
    this.healthCareProvider.address = this.updateForm.value.address;
    this.healthCareProvider.email = this.updateForm.value.email;
    this.healthCareProvider.providerGender = this.updateForm.value.providerGender;
    this.healthCareProvider.password = this.updateForm.value.password;
    this.healthCareProviderService.updateHealthCareProvider(JSON.parse(this.cookieService.get('userId')).userToken, this.healthCareProvider)
      .subscribe(data => {
        alert('Profile updated successfully');
        location.reload();
      }, error => alert("Failed to update profile"))
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