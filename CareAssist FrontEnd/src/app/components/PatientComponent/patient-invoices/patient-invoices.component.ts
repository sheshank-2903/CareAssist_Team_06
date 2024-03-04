import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Invoices } from 'src/app/model/Invoices';
import { InvoicesService } from 'src/app/services/InvoicesServices/invoices.service';

@Component({
  selector: 'app-patient-invoices',
  templateUrl: './patient-invoices.component.html',
  styleUrls: ['./patient-invoices.component.css']
})
export class PatientInvoicesComponent {

  invoiceList:Invoices[]=[];
  search!:any;

  constructor(private invoiceService:InvoicesService,private cookieService: CookieService){

    this.getInvoicesByPatientId();
  }

  getInvoicesByPatientId(){
    this.search=undefined;
    this.invoiceService.getInvoiceByPatientId(JSON.parse(this.cookieService.get('userId')).userId,JSON.parse(this.cookieService.get('userId')).userToken)
    .subscribe(invoices=>this.invoiceList=invoices,error=> alert("Failed to get Invoice"))

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
