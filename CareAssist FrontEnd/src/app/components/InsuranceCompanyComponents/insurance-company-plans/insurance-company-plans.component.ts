import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Plans } from 'src/app/model/Plans';
import { PlansService } from 'src/app/services/PlansServices/plans.service';

@Component({
  selector: 'app-insurance-company-plans',
  templateUrl: './insurance-company-plans.component.html',
  styleUrls: ['./insurance-company-plans.component.css']
})
export class InsuranceCompanyPlansComponent {
  isAddPlanModelVisible: boolean = false;
  isEditPlanModelVisible: boolean = false;
  addPlanForm !: FormGroup;
  editPlanForm !: FormGroup;
  confirmDeleteInput!: string;
  search: any
  comapnyPlansList: Plans[] = [];
  deleteId!: number;
  editId!: number;


  constructor(private formBuilder: FormBuilder, private plansService: PlansService, private cookieService: CookieService) {
    this.getPlansByCompanyId();
  }

  ngOnInit() {
    this.addPlanForm = this.formBuilder.group({
      PlanName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]{3,20}$')]],
      PlanAmount: ['', [Validators.required, Validators.pattern('^[1-9]\\d{4,}$')]],
      descriptionOfPlan: ['', [Validators.required]]
    })

    this.editPlanForm = this.formBuilder.group({
      PlanName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]{3,20}$')]],
      PlanAmount: ['', [Validators.required, Validators.pattern('^[1-9]\\d{4,}$')]],
      descriptionOfPlan: ['', [Validators.required]]
    })
  }

  getPlansByCompanyId() {
    this.search=undefined;
    this.plansService.getPlansByCompanyId(JSON.parse(this.cookieService.get('userId')).userId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(plans => this.comapnyPlansList = plans,error=>{alert("Unable to fetch Plan");});
  }


  toggleAddPlan() {
    let addModel = document.getElementById("addPlanFormModel");
    if (this.isAddPlanModelVisible) {
      addModel?.classList.remove("active");
      this.isAddPlanModelVisible = false;
    }
    else {
      addModel?.classList.add("active");
      this.isAddPlanModelVisible = true;
    }
  }


  toggleEditPlan(planId: number) {
    this.editId = planId;
    let addModel = document.getElementById("editPlanFormModel");
    if (this.isEditPlanModelVisible) {
      addModel?.classList.remove("active");
      this.isEditPlanModelVisible = false;
    }
    else {
      addModel?.classList.add("active");
      this.isEditPlanModelVisible = true;
    }
  }

  get getPlanForm() {
    return this.addPlanForm.controls;
  }

  get getEditPlanForm() {
    return this.editPlanForm.controls;
  }




  SubmitPlan() {
    if (this.addPlanForm.invalid) {
      return;
    }
    const plan: Plans = {
      "planId": 0,
      "planName": this.addPlanForm.get("PlanName")?.value,
      "description": this.addPlanForm.get("descriptionOfPlan")?.value,
      "dateOfIssue": new Date(),
      "coverageAmount": this.addPlanForm.get("PlanAmount")?.value
    }

    this.plansService.addPlans(plan, JSON.parse(this.cookieService.get('userId')).userId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(plan => {
        alert('Plan generated successfully');
        this.addPlanForm.reset();
        this.toggleAddPlan();
        this.getPlansByCompanyId();
      }, (error) => { alert("Failed to generate plan") })

  }

  SubmitEditedPlan() {
    if (this.editPlanForm.invalid) {
      return;
    }
    this.plansService.updatePlans(this.editPlanForm.get("PlanName")?.value, this.editPlanForm.get("descriptionOfPlan")?.value, this.editPlanForm.get("PlanAmount")?.value, this.editId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(plan => {
        alert('Plan updated successfully');
        this.editPlanForm.reset();
        this.toggleEditPlan(1);
        this.getPlansByCompanyId();
      }, (error) => { alert("Failed to update plan") })


  }

  confirmDelete(planId: number) {
    this.deleteId = planId
    let content = document.getElementById('confirmDeleteDisplay');
    content?.classList.add('active');
  }

  closeDeleteModel() {
    let content = document.getElementById('confirmDeleteDisplay');
    content?.classList.remove('active');
  }

  submitConfirmDelete() {
    this.deletePlanById();
    let content = document.getElementById('confirmDeleteDisplay');
    content?.classList.remove('active');
  }

  deletePlanById() {
    this.plansService.deletePlanById(JSON.parse(this.cookieService.get('userId')).userToken, this.deleteId)
      .subscribe(message => {
        this.deleteId = 0;
        alert('Plan Deleted Successfully');
        this.getPlansByCompanyId();
        this.confirmDeleteInput = "";
      }, error => alert("Cannot Delete Plan As It Is Purchased By Patients!!!"))
  }

  searchPlanByName() {
    if (this.search == null || typeof this.search !== 'string') alert("invalid Input for search by name");
    else {
      this.plansService.getPlansByNameAndCompanyId(JSON.parse(this.cookieService.get('userId')).userToken, this.search, JSON.parse(this.cookieService.get('userId')).userId)
        .subscribe((planList) => {
          this.comapnyPlansList = planList;
        })
    }

  }
  searchPlanById() {
    const parsedNumber: number = parseInt(this.search, 10);
    if (this.search == null || isNaN(parsedNumber)) alert("invalid Input for search by Id");
    else {
      this.comapnyPlansList = [];
      this.plansService.getPlansById(this.search, JSON.parse(this.cookieService.get('userId')).userToken)
        .subscribe((plan) => {
          this.comapnyPlansList = this.comapnyPlansList.concat(plan);
        })
    }
  }
}
