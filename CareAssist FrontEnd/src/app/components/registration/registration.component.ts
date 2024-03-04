import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HealthCareProvider } from 'src/app/model/HealthCareProvider';
import { InsuranceCompany } from 'src/app/model/InsuranceCompany';
import { Patient } from 'src/app/model/Patient';
import { HealthCareProviderService } from 'src/app/services/HealthCareProviderServices/health-care-provider.service';
import { InsuranceCompanyService } from 'src/app/services/InsuranceCompanyServices/insurance-company.service';
import { PatientService } from 'src/app/services/PatientServices/patient.service';
import { HomeComponent } from '../HomeComponents/home/home.component';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  patientRegistrationForm !: FormGroup;
  insuranceRegistrationForm !: FormGroup;
  HealthCareRegistrationForm !: FormGroup;

  constructor(private formBuilder: FormBuilder, private healthCareService: HealthCareProviderService
    , private patientService: PatientService, private insuranceService: InsuranceCompanyService,
    private cookieService:CookieService) {
      this.cookieService.delete('userId', '/', 'localhost');

  }

  ngOnInit() {
    this.patientRegistrationForm = this.formBuilder.group({
      patientName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]{3,20}$')]],
      contact: ['', [Validators.required, Validators.pattern('\\d{10}')]],
      dob: ['', [Validators.required]],
      address: ['', [Validators.required]],
      descriptionOfTreatment: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$')]],
      confirm_password: ['', Validators.required],
      patientGender: ['MALE', Validators.required],
    }, { validator: this.passwordMatchValidator });

    this.insuranceRegistrationForm = this.formBuilder.group({
      insuranceCompanyDescription: ['', [Validators.required, Validators.minLength(20)]],
      companyName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]{5,20}$')]],
      companyContactNumber: ['', [Validators.required, Validators.pattern('\\d{10}')]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$')]],
      confirm_password: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });

    this.HealthCareRegistrationForm = this.formBuilder.group({
      healthCareProviderName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]{3,20}$')]],
      address: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$')]],
      confirm_password: ['', Validators.required],
      providerGender: ['MALE', Validators.required],
    }, { validator: this.passwordMatchValidator });
  }

  get getInsuranceForm() {

    return this.insuranceRegistrationForm.controls;
  }

  get getHealthCareProvider() {

    return this.HealthCareRegistrationForm.controls;
  }


  get getPatientForm() {

    return this.patientRegistrationForm.controls;
  }

  onSubmitPatinet() {
    if (this.patientRegistrationForm.invalid) {
      return;
    }
    const patient: Patient = {
      patientId: 0,
      dob: this.patientRegistrationForm.value.dob,
      contact: this.patientRegistrationForm.value.contact,
      address: this.patientRegistrationForm.value.address,
      patientName: this.patientRegistrationForm.value.patientName,
      descriptionOfTreatment: this.patientRegistrationForm.value.descriptionOfTreatment,
      email: this.patientRegistrationForm.value.email.toLowerCase(),
      password: this.patientRegistrationForm.value.password,
      patientGender: this.patientRegistrationForm.value.patientGender
    }
    this.patientService.addPatient(patient)
      .subscribe(
        (patient) => {
          alert('You have been registered successfully');
          this.patientRegistrationForm.reset();
          new HomeComponent().openTab("login");
        }, error => { alert("Not able to Register patient") }
      );
  }

  onSubmitHealthCare() {
    if (this.HealthCareRegistrationForm.invalid) {
      return;
    }
    const healthCareProvider: HealthCareProvider = {
      healthCareProviderId: 0,
      healthCareProviderName: this.HealthCareRegistrationForm.value.healthCareProviderName,
      address: this.HealthCareRegistrationForm.value.address,
      email: this.HealthCareRegistrationForm.value.email.toLowerCase(),
      password: this.HealthCareRegistrationForm.value.password,
      providerGender: this.HealthCareRegistrationForm.value.providerGender
    };
    this.healthCareService.addHealthCareProvider(healthCareProvider)
      .subscribe(
        (healthCareProviders) => {
          alert('You have been Registered Successfully');
          this.HealthCareRegistrationForm.reset();
          new HomeComponent().openTab("login");
        }, error => { alert("Not able to Register Health Care Provider") }
      );
  }

  onSubmitInsuranceCompany() {
    if (this.insuranceRegistrationForm.invalid) {
      return;
    }
    const insuranceCompany: InsuranceCompany = {
      insuranceCompanyId: 0,
      insuranceCompanyDescription: this.insuranceRegistrationForm.value.insuranceCompanyDescription,
      companyName: this.insuranceRegistrationForm.value.companyName,
      companyContactNumber: this.insuranceRegistrationForm.value.companyContactNumber,
      email: this.insuranceRegistrationForm.value.email.toLowerCase(),
      password: this.insuranceRegistrationForm.value.password
    };
    this.insuranceService.addInsuranceCompany(insuranceCompany)
      .subscribe(
        (insuranceCompany) => {
          alert('You have been registered successfully');
          this.insuranceRegistrationForm.reset();
          new HomeComponent().openTab("login");
        }, error => { alert("Not able to Register Insurance Company") }
      )
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

  getYesterdayDate(): string {
    const today = new Date();
    today.setDate(today.getDate() - 1);
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    const yyyy = today.getFullYear();

    return `${yyyy}-${mm}-${dd}`;
  }

  openTab(tabId: string): void {

    const tabContents: NodeListOf<Element> = document.querySelectorAll('.tab-content');
    tabContents.forEach((content: Element) => {
      content.classList.remove('active');
    });
    const tab: NodeListOf<Element> = document.querySelectorAll('.tab');
    tab.forEach((content: Element) => {
      content.classList.remove('active');
    });

    const selectedTabContent: Element | null = document.getElementById(tabId);
    if (selectedTabContent) {
      selectedTabContent.classList.add('active');
    }
    const selectedTab: Element | null = document.getElementById("button-"+tabId);
    if (selectedTab) {
      selectedTab.classList.add('active');
    }

    new HomeComponent().setSelectedTabActive("register");
  }
}
