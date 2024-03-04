import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule}  from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



import { HealthCareProviderComponent } from './components/HealthCareProviderComponents/health-care-provider/health-care-provider.component';
import { ClaimComponent } from './components/ClaimComponents/claim/claim.component';
import { InsuranceCompanyComponent } from './components/InsuranceCompanyComponents/insurance-company/insurance-company.component';
import { InvoicesComponent } from './components/InvoicesComponents/invoices/invoices.component';
import { PatientComponent } from './components/PatientComponent/patient/patient.component';
import { PlansComponent } from './components/PlansComponents/plans/plans.component';
import { AdminHomeComponent } from './components/AdminComponents/admin-home/admin-home.component';
import { AdminPatientComponent } from './components/AdminComponents/admin-patient/admin-patient.component';
import { AdminHealthCareProviderComponent } from './components/AdminComponents/admin-health-care-provider/admin-health-care-provider.component';
import { AdminInsuranceCompanyComponent } from './components/AdminComponents/admin-insurance-company/admin-insurance-company.component';
import { AdminPlansComponent } from './components/AdminComponents/admin-plans/admin-plans.component';
import { AdminClaimsComponent } from './components/AdminComponents/admin-claims/admin-claims.component';
import { AdminInvoicesComponent } from './components/AdminComponents/admin-invoices/admin-invoices.component';

import { InsuranceCompanyPlansComponent } from './components/InsuranceCompanyComponents/insurance-company-plans/insurance-company-plans.component';
import { InsuranceCompanyClaimsComponent } from './components/InsuranceCompanyComponents/insurance-company-claims/insurance-company-claims.component';
import { InsuranceCompanyProfileComponent } from './components/InsuranceCompanyComponents/insurance-company-profile/insurance-company-profile.component';

import { HealthCareProviderHomeComponent } from './components/HealthCareProviderComponents/health-care-provider-home/health-care-provider-home.component';
import { HealthCareProviderPatientComponent } from './components/HealthCareProviderComponents/health-care-provider-patient/health-care-provider-patient.component';
import { HealthCareProviderInvoiceHistoryComponent } from './components/HealthCareProviderComponents/health-care-provider-invoice-history/health-care-provider-invoice-history.component';


import { PatientHomeComponent } from './components/PatientComponent/patient-profile/patient-home.component';
import { PatientPlansComponent } from './components/PatientComponent/patient-plans/patient-plans.component';
import { PatientPurchasedPlansComponent } from './components/PatientComponent/patient-purchased-plans/patient-purchased-plans.component';
import { PatientRequestInvoiceComponent } from './components/PatientComponent/patient-request-invoice/patient-request-invoice.component';
import { PatientInvoicesComponent } from './components/PatientComponent/patient-invoices/patient-invoices.component';
import { PatientClaimsComponent } from './components/PatientComponent/patient-claims/patient-claims.component';
import { HealthCareProviderProfileComponent } from './components/HealthCareProviderComponents/health-care-provider-profile/health-care-provider-profile.component';
import { AdminProfileComponent } from './components/AdminComponents/admin-profile/admin-profile.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { AboutUsComponent } from './components/HomeComponents/about-us/about-us.component';
import { FeedbackComponent } from './components/HomeComponents/feedback/feedback.component';
import { HomeComponent } from './components/HomeComponents/home/home.component';
import { AdminComponent } from './components/AdminComponents/admin/admin.component';





@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    HealthCareProviderComponent,
    ClaimComponent,
    InsuranceCompanyComponent,
    InvoicesComponent,
    PatientComponent,
    PlansComponent,
    AdminHomeComponent,
    AdminPatientComponent,
    AdminHealthCareProviderComponent,
    AdminInsuranceCompanyComponent,
    AdminPlansComponent,
    AdminClaimsComponent,
    AdminInvoicesComponent,

    InsuranceCompanyPlansComponent,
    InsuranceCompanyClaimsComponent,
    InsuranceCompanyProfileComponent,

    HealthCareProviderHomeComponent,
    HealthCareProviderPatientComponent,
    HealthCareProviderInvoiceHistoryComponent,
    PatientHomeComponent,
    PatientPlansComponent,
    PatientPurchasedPlansComponent,
    PatientRequestInvoiceComponent,
    PatientInvoicesComponent,
    PatientClaimsComponent,
    HealthCareProviderProfileComponent,
    AdminProfileComponent,
    LoginComponent,
    RegistrationComponent,
    AboutUsComponent,
    FeedbackComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
