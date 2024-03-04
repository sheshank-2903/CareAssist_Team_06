import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { InsuranceCompany } from 'src/app/model/InsuranceCompany';
import { InsuranceCompanyService } from 'src/app/services/InsuranceCompanyServices/insurance-company.service';

@Component({
  selector: 'app-admin-insurance-company',
  templateUrl: './admin-insurance-company.component.html',
  styleUrls: ['./admin-insurance-company.component.css']
})
export class AdminInsuranceCompanyComponent {
  insuranceCompanyList: InsuranceCompany[] = [];
  deleteId!: number;
  search: any;
  confirmation!:string;

  constructor(private insuranceCompanyService: InsuranceCompanyService, private cookieService: CookieService) {
    this.getAllInsuranceCompany();
  }
  getAllInsuranceCompany() {
    this.search=undefined;
    this.insuranceCompanyService.getAllInsuranceCompany(JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(
        (insuranceCompany) => {
          this.insuranceCompanyList = insuranceCompany;
        },error=>{alert("Please try Again! Error Occured");}
      );
  }

  confirmDelete(deleteInsuranceCompanyId: number) {
    this.deleteId = deleteInsuranceCompanyId;
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
    this.deleteInsuranceCompanyId(this.deleteId);
    alert('Delete completed');
    let content = document.getElementById('confirmDeleteDisplay');
    content?.classList.remove('active');
  }

  deleteInsuranceCompanyId(deleteId: number) {
    this.insuranceCompanyService.deleteInsuranceCompanyById(JSON.parse(this.cookieService.get('userId')).userToken, deleteId)
      .subscribe(
        (insuranceCompany) => {
          this.deleteId = 0;
          this.getAllInsuranceCompany();
        },error=>{alert("This Company has some active purchased plans cannot delete");}
      );
  }
  searchInsuranceCompanyByName() {
    if (this.search == null || typeof this.search !== 'string') alert("invalid Input for search by name");
    else {
      this.insuranceCompanyService.getInsuranceCompanyByName(JSON.parse(this.cookieService.get('userId')).userToken, this.search)
        .subscribe((insuranceCompanyList) => {
          this.insuranceCompanyList = insuranceCompanyList;
        })
    }

  }
  searchInsuranceCompanyById() {
    const parsedNumber: number = parseInt(this.search, 10);
    if (this.search == null || isNaN(parsedNumber)) alert("invalid Input for search by Id");
    else {
      this.insuranceCompanyList = [];
      this.insuranceCompanyService.getInsuranceCompanyById(this.search, JSON.parse(this.cookieService.get('userId')).userToken)
        .subscribe((insuranceCompany) => {
          this.insuranceCompanyList = this.insuranceCompanyList.concat(insuranceCompany);
        })
    }
  }

}
