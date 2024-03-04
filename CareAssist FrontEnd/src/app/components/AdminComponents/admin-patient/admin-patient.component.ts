import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Patient } from 'src/app/model/Patient';
import { PatientService } from 'src/app/services/PatientServices/patient.service';

@Component({
  selector: 'app-admin-patient',
  templateUrl: './admin-patient.component.html',
  styleUrls: ['./admin-patient.component.css']
})
export class AdminPatientComponent {
  patientList: Patient[] = [];
  deleteId!: number;
  search: any;
  confirmation!:string;

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

  confirmDelete(deletePatientId: number) {
    this.deleteId = deletePatientId;
    let content = document.getElementById('confirmDeleteDisplay');
    content?.classList.add('active');
  }

  closeDeleteModel() {
    this.confirmation="";
    let content = document.getElementById('confirmDeleteDisplay');
    content?.classList.remove('active');
  }

  submitConfirmDelete() {
    this.confirmation="";
    this.deletePatientId(this.deleteId);
    alert('Delete completed');
    let content = document.getElementById('confirmDeleteDisplay');
    content?.classList.remove('active');
    this.getAllPatients();
  }

  deletePatientId(deleteId: number) {
    this.patientService.deletePatientById(JSON.parse(this.cookieService.get('userId')).userToken, deleteId)
      .subscribe(
        (admin) => {
          this.deleteId = 0;
          this.getAllPatients();
        },error=>{alert("Failed to delete Patient");}
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
        }
      )
    }

  }

}
