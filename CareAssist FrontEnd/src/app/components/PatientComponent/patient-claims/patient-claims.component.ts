import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Claims } from 'src/app/model/Claims';
import { Plans } from 'src/app/model/Plans';
import { ClaimsService } from 'src/app/services/ClaimsServices/claims.service';

@Component({
  selector: 'app-patient-claims',
  templateUrl: './patient-claims.component.html',
  styleUrls: ['./patient-claims.component.css']
})
export class PatientClaimsComponent {

  claimsList:Claims[]=[];
  search!:any;

  plan:Plans={
    "planId":0,
    "planName":'',
    "dateOfIssue":new Date(),
    "coverageAmount":0,
    "description":''
  }


  constructor(private claimService:ClaimsService,private cookieService: CookieService){
    this.getClaimsByPatientId();

  }

  getClaimsByPatientId(){
    this.search=undefined;
    this.claimService.getClaimsByPatientId(JSON.parse(this.cookieService.get('userId')).userId,JSON.parse(this.cookieService.get('userId')).userToken)
    .subscribe(claims=>this.claimsList=claims,error=> alert("Failed to get Claims"))
  }

  showPlanDetails(claimId:number){
    this.claimService.getPlanByClaimId(claimId,JSON.parse(this.cookieService.get('userId')).userToken)
    .subscribe(data=>this.plan=data,error=> alert("Failed to get Plans"))

    let content=document.getElementById('planDetailsDisplay');
    content?.classList.add('active');
  }

  closePlanModel(){
    let content=document.getElementById('planDetailsDisplay');
    content?.classList.remove('active');
  }

  searchClaimById() {
    this.claimsList = [];
      this.claimService.getClaimsById(this.search, JSON.parse(this.cookieService.get('userId')).userToken).
        subscribe(data => {
          this.claimsList = this.claimsList.concat(data);
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

}
