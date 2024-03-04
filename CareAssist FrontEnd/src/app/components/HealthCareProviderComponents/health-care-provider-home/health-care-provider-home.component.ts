import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Invoices } from 'src/app/model/Invoices';
import { InvoicesService } from 'src/app/services/InvoicesServices/invoices.service';

@Component({
  selector: 'app-health-care-provider-home',
  templateUrl: './health-care-provider-home.component.html',
  styleUrls: ['./health-care-provider-home.component.css']
})
export class HealthCareProviderHomeComponent {
  isAddAdminModelVisible: boolean = false;
  invoiceList: Invoices[] = [];
  currentInvoiceId!: number;
  search!: any;
  constructor(private invoiceService: InvoicesService, private cookieService: CookieService) {
    this.getAllInvoice();
  }
  getAllInvoice() {
    this.search=undefined;
    this.invoiceService.getInvoiceByHealthCareProviderId(JSON.parse(this.cookieService.get('userId')).userToken, JSON.parse(this.cookieService.get('userId')).userId)
      .subscribe(
        (patients) => {
          this.invoiceList = patients
        },error=>{alert("Please try Again! Error Occured");}
      );
  }

  approveInvoiceAction() {
    this.invoiceService.updateInvoiceStatus(JSON.parse(this.cookieService.get('userId')).userToken, this.currentInvoiceId, "APPROVED")
      .subscribe((invoice) => {
        alert("Status Approved");
        this.toggleChangeStatus(0);
        this.getAllInvoice();
      })
  }
  rejectInvoiceAction() {
    this.invoiceService.updateInvoiceStatus(JSON.parse(this.cookieService.get('userId')).userToken, this.currentInvoiceId, "REJECTED")
      .subscribe(() => {
        alert("Status Rejected");
        this.toggleChangeStatus(0);
        this.getAllInvoice();
      })
  }

  toggleChangeStatus(currentInvoiceId: number) {
    this.currentInvoiceId = currentInvoiceId;
    let statusModel = document.getElementById("changeStatusModel"); 
    if (this.isAddAdminModelVisible) {
      statusModel?.classList.remove("active");
      this.isAddAdminModelVisible = false;
    }
    else {
      statusModel?.classList.add("active");
      this.isAddAdminModelVisible = true;
    }
  }

  searchInvoiceById() {
    this.invoiceList = [];
    this.invoiceService.getInvoiceById(this.search, JSON.parse(this.cookieService.get('userId')).userToken).
      subscribe(data => {
        this.invoiceList = this.invoiceList.concat(data);
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
