import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Plans } from 'src/app/model/Plans';
import { PatientService } from 'src/app/services/PatientServices/patient.service';
import { PlansService } from 'src/app/services/PlansServices/plans.service';

@Component({
  selector: 'app-patient-plans',
  templateUrl: './patient-plans.component.html',
  styleUrls: ['./patient-plans.component.css']
})
export class PatientPlansComponent {


  plansList: Plans[] = [];
  confirmInput!: string;
  selectedPlanId!: number;
  search!: any;


  constructor(private plansService: PlansService, private patientService: PatientService, private cookieService: CookieService) {
    this.getAllPlans();
  }

  getAllPlans() {
    this.search=undefined;
    this.plansService.getAllPlans(JSON.parse(this.cookieService.get('userId')).userToken)
    .subscribe((plans) => { this.plansList = plans },error=> alert("Failed to get Plans"));
  }


  confirmPurchase(planId: number) {
    this.selectedPlanId = planId;
    let content = document.getElementById('confirmPurchaseDisplay');
    content?.classList.add('active');
  }

  closeConfirmPurchase() {
    let content = document.getElementById('confirmPurchaseDisplay');
    content?.classList.remove('active');
  }

  confirmPurchaseCompleted() {
    this.patientService.purchasePlan(JSON.parse(this.cookieService.get('userId')).userId, this.selectedPlanId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(
        (data) => {
          if (data.message == "Purchase Successfull")
            alert('Congratulations Purchase completed');
          else { alert('Purchase failed'); }
        }
      );
    this.confirmInput = "";
    let content = document.getElementById('confirmPurchaseDisplay');
    content?.classList.remove('active');
  }

  searchPlanByName() {
    if (this.search == null || typeof this.search !== 'string') alert("invalid Input for search by name");
    else {
      this.plansService.getPlansByName(JSON.parse(this.cookieService.get('userId')).userToken, this.search)
        .subscribe((planList) => {
          this.plansList = planList;
        })
    }

  }
  searchPlanById() {
    const parsedNumber: number = parseInt(this.search, 10);
    if (this.search == null || isNaN(parsedNumber)) alert("invalid Input for search by Id");
    else {
      this.plansList = [];
      this.plansService.getPlansById(this.search, JSON.parse(this.cookieService.get('userId')).userToken)
        .subscribe((plan) => {
          this.plansList = this.plansList.concat(plan);
        })
    }
  }
}
