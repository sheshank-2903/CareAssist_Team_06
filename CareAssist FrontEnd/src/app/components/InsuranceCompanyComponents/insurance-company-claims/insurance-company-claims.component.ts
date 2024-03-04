import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Claims } from 'src/app/model/Claims';
import { Patient } from 'src/app/model/Patient';
import { Plans } from 'src/app/model/Plans';
import { ClaimsService } from 'src/app/services/ClaimsServices/claims.service';
import { PlansService } from 'src/app/services/PlansServices/plans.service';

@Component({
  selector: 'app-insurance-company-claims',
  templateUrl: './insurance-company-claims.component.html',
  styleUrls: ['./insurance-company-claims.component.css']
})
export class InsuranceCompanyClaimsComponent {
  search: any;

  plan: Plans = {
    "planId": 0,
    "planName": '',
    "dateOfIssue": new Date(),
    "coverageAmount": 0,
    "description": ''
  }

  patient: Patient = {
    "patientId": 0,
    'dob': new Date(),
    "contact": "",
    "address": "",
    "patientName": "",
    "descriptionOfTreatment": "",
    "email": "",
    "password": "",
    "patientGender": "",
  }


  claimList: Claims[] = [];

  claimId!: number;
  confirmApproveInput!: string;
  confirmRejectInput!: string;


  constructor(private claimService: ClaimsService, private cookieService: CookieService, private planService: PlansService) {
    this.getClaimsByCompanyId();
  }

  getClaimsByCompanyId() {
    this.search=undefined;
    this.claimService.getClaimsByCompanyId(JSON.parse(this.cookieService.get('userId')).userId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(claims => this.claimList = claims, error => { alert("Please try Again! Error Occured"); });
  }

  showPatientModel(claimId: number) {
    this.claimService.getPatientByClaimId(claimId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(data => this.patient = data, error => { alert("unable to fetch Patient Details"); });

    let content = document.getElementById('showPatientModel');
    content?.classList.add('active');
  }

  closePatientModel() {
    let content = document.getElementById('showPatientModel');
    content?.classList.remove('active');
  }

  showPlanModel(claimId: number) {
    this.claimService.getPlanByClaimId(claimId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(data => this.plan = data, error => { alert("Unable to fetch Plain Details"); });
    let content = document.getElementById('showPlanModel');
    content?.classList.add('active');
  }

  closePlanModel() {
    let content = document.getElementById('showPlanModel');
    content?.classList.remove('active');
  }

  confirmApprove(claimId: number) {
    this.claimId = claimId;
    let content = document.getElementById('confirmApproveDisplay');
    content?.classList.add('active');
  }

  closeApproveModel() {
    this.confirmApproveInput = "";
    let content = document.getElementById('confirmApproveDisplay');
    content?.classList.remove('active');
  }

  submitApprove() {
    this.claimService.updateClaims(this.claimId, "APPROVED", JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe((claim) => {
        alert("Claim has been approved");
        this.confirmApproveInput = "";
        this.getClaimsByCompanyId();
        this.closeApproveModel();
      }, error => alert("Failed to approve claim"))
  }

  confirmReject(claimId: number) {
    this.claimId = claimId;
    let content = document.getElementById('confirmRejectDisplay');
    content?.classList.add('active');
  }

  closeRejectModel() {
    this.confirmRejectInput = "";
    let content = document.getElementById('confirmRejectDisplay');
    content?.classList.remove('active');
  }


  submitReject() {
    this.claimService.updateClaims(this.claimId, "REJECTED", JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe((claim) => {
        alert("Claim has been rejected");
        this.getClaimsByCompanyId();
        this.confirmRejectInput = "";
        this.getClaimsByCompanyId();
        this.closeRejectModel();
      }, error => alert("Failed to reject claim"))
  }

  searchClaimsById() {
    this.claimList = [];
    this.claimService.getClaimsById(this.search, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe((claim) => {
        this.claimList = this.claimList.concat(claim);
      })
  }

  searchClaimsByPatientId() {
    this.claimService.getClaimsByPatientId(this.search, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe((claimList) => {
        this.claimList = claimList;
      })
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'PENDING':
        return 'blue';
      case 'APPROVED':
        return 'green';
      case 'REJECTED':
        return 'red';
      default:
        return 'black';
    }
  }

  getDisplayActionText(status: string) {
    if (status !== "PENDING")
      return ""
    else return "none"
  }

  getDisplayActionButton(status: string) {
    if (status === "PENDING")
      return ""
    else return "none"
  }

}
