import { Component } from '@angular/core';
import { HomeComponent } from '../HomeComponents/home/home.component';
import { AuthRequest } from 'src/app/model/AuthRequest';
import { JwtServiceService } from 'src/app/services/LoginServices/jwt-service.service';
import { Patient } from 'src/app/model/Patient';
import { Admin } from 'src/app/model/Admin';
import { HealthCareProvider } from 'src/app/model/HealthCareProvider';
import { InsuranceCompany } from 'src/app/model/InsuranceCompany';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  response: any;
  token: any;
  role: string = "PATIENT";

  authRequest: AuthRequest = {
    email: "",
    password: ""
  };

  goToRegistration() {
    new HomeComponent().openTab("register");
  }

  constructor(private jwtService: JwtServiceService, private cookieService: CookieService, private router:Router) {
    this.cookieService.delete('userId', '/', 'localhost');
   }

    
  readFormData(formData: any) {
    this.authRequest.email = formData.form.value.email.toLowerCase();
    this.authRequest.password = formData.form.value.password;
    this.getAccessToken(this.authRequest);
  }

  public getAccessToken(authRequest: any) {
    let response = this.jwtService.getGeneratedToken(authRequest, this.role);

    response
    .subscribe((genToken: any) => {
        this.token = genToken;
        this.accessApi(this.token);
      },
      (error:any) => {
        alert("Please check email and password");
      }
    );
  }

  public accessApi(token: any) {
    let responseBody = this.jwtService.authorizationTest(token, this.authRequest.email);
    responseBody.subscribe(responseData => {
      this.response = responseData;
      if (this.response) {
        if (this.role === "PATIENT"){
          const patient:Patient=JSON.parse(this.response);
          this.cookieService.set('userId', JSON.stringify({userId: patient.patientId , userToken:token}));
          this.router.navigate(['/patient/browsePlans']);
        }
        if (this.role === "ADMIN"){
          const admin:Admin=JSON.parse(this.response);
          this.cookieService.set('userId', JSON.stringify({userId: admin.adminId , userToken:token}));
          this.router.navigate(['/admin/home']);
        }
        if (this.role === "HEALTH_CARE_PROVIDER"){
          const healthCareProvider:HealthCareProvider=JSON.parse(this.response);
          this.cookieService.set('userId', JSON.stringify({userId: healthCareProvider.healthCareProviderId , userToken:token}));
          this.router.navigate(['/healthCareProvider/home']);
        }
        if (this.role === "INSURANCE_COMPANY"){
          const insuranceCompany:InsuranceCompany=JSON.parse(this.response);
          this.cookieService.set('userId', JSON.stringify({userId: insuranceCompany.insuranceCompanyId , userToken:token}));
          this.router.navigate(['/insuranceCompany/home']);
        }
      }
    }, error => { alert("please try again") });
  }

}
