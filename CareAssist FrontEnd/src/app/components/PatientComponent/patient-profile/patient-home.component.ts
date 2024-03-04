import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Patient } from 'src/app/model/Patient';
import { PatientService } from 'src/app/services/PatientServices/patient.service';

@Component({
  selector: 'app-patient-home',
  templateUrl: './patient-home.component.html',
  styleUrls: ['./patient-home.component.css']
})
export class PatientHomeComponent {

  editable:boolean=true;

  updateForm !: FormGroup;

  patient:Patient={
    patientId: 0,
    dob: new Date(),
    contact: '',
    address: '',
    patientName: '',
    descriptionOfTreatment: '',
    email: '',
    password: '',
    patientGender: ''
  }

  constructor(private formBuilder: FormBuilder,private patientService:PatientService,private cookieService:CookieService){

    this.patientService.getPatientById(JSON.parse(this.cookieService.get('userId')).userId, JSON.parse(this.cookieService.get('userId')).userToken)
    .subscribe(
      patient=>{
        this.patient=patient;
        this.updateForm=this.formBuilder.group({
          patientId:[this.patient.patientId],
          patientName:[this.patient.patientName,[Validators.required,Validators.pattern('^[a-zA-Z ]{3,20}$')]],
          contact:[this.patient.contact,[Validators.required,Validators.pattern('\\d{10}')]],
          dob:[this.patient.dob,[Validators.required]],
          address:[this.patient.address,[Validators.required]],
          descriptionOfTreatment:[this.patient.descriptionOfTreatment,[Validators.required]],
          email:[this.patient.email,[Validators.required,Validators.email]],
          patientGender:[this.patient.patientGender],
          password:['',[Validators.required,Validators.pattern('^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$')]],
          confirm_password: ['', Validators.required] 
        },{validator: this.passwordMatchValidator});
      },error=> alert("Failed to get Patient Information")
    )
  }


  get f(){
    return this.updateForm.controls;
  }


  toggleEditable(){
    this.editable=!this.editable;
    if(this.editable){
      location.reload();
    }
  }

  onSubmit(){

    if(this.updateForm.invalid){
      return;
  }

  this.patient.patientName=this.updateForm.value.patientName;
  this.patient.contact=this.updateForm.value.contact;
  this.patient.dob=this.updateForm.value.dob;
  this.patient.address=this.updateForm.value.address;
  this.patient.descriptionOfTreatment=this.updateForm.value.descriptionOfTreatment;
  this.patient.email=this.updateForm.value.email;
  this.patient.password=this.updateForm.value.password;
  this.patient.patientGender=this.updateForm.get('patientGender')?.value

  this.patientService.updatePatient(this.patient, JSON.parse(this.cookieService.get('userId')).userToken)
  .subscribe(patient=>{
    alert('Profile updated successfully');
    location.reload();
  },error=>alert("Failed to update Profile"))
  
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
    today.setDate(today.getDate() - 1); // Subtract 1 day to get yesterday's date
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
    const yyyy = today.getFullYear();
  
    return `${yyyy}-${mm}-${dd}`;
  }
}


