import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Invoices } from 'src/app/model/Invoices';
import { InvoicesService } from 'src/app/services/InvoicesServices/invoices.service';

@Component({
  selector: 'app-health-care-provider-invoice-history',
  templateUrl: './health-care-provider-invoice-history.component.html',
  styleUrls: ['./health-care-provider-invoice-history.component.css']
})
export class HealthCareProviderInvoiceHistoryComponent {

  invoiceList: Invoices[] = [];
  search!:any;
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
}
