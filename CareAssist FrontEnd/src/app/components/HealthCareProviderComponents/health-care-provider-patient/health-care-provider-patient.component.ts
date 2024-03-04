import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Patient } from 'src/app/model/Patient';
import { PatientService } from 'src/app/services/PatientServices/patient.service';

@Component({
  selector: 'app-health-care-provider-patient',
  templateUrl: './health-care-provider-patient.component.html',
  styleUrls: ['./health-care-provider-patient.component.css']
})
export class HealthCareProviderPatientComponent {
  patientList: Patient[] = [];
  search: any;
  constructor(private patientService: PatientService, private cookieService: CookieService) {
    this.getAllPatients();
  }
  getAllPatients() {
    this.search=undefined;
    this.patientService.getAllPatients(JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(
        (patients) => {
          this.patientList = patients
        },error=>{alert("Please try Again! Error Occured");}
      );
  }

  searchPatientByName() {
    if (this.search == null || typeof this.search !== 'string') alert("invalid Input for search by name");
    else {
      this.patientService.getPatientByName(this.search, JSON.parse(this.cookieService.get('userId')).userToken)
        .subscribe((patientList) => {
          this.patientList = patientList;
        })
    }
  }

  searchPatientById() {
    const parsedNumber: number = parseInt(this.search, 10);
    if (this.search == null || isNaN(parsedNumber)) alert("invalid Input for search by Id");
    else {
      this.patientList = [];
      this.patientService.getPatientById(this.search, JSON.parse(this.cookieService.get('userId')).userToken)
        .subscribe((patient) => {
          this.patientList = this.patientList.concat(patient);
        })
    }

  }
}
