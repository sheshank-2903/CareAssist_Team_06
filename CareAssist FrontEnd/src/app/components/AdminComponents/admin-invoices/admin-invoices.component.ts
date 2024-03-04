import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Invoices } from 'src/app/model/Invoices';
import { InvoicesService } from 'src/app/services/InvoicesServices/invoices.service';

@Component({
  selector: 'app-admin-invoices',
  templateUrl: './admin-invoices.component.html',
  styleUrls: ['./admin-invoices.component.css']
})
export class AdminInvoicesComponent {

  invoiceList: Invoices[] = [];
  deleteId!: number;
  search!: any;
  confirmation!:string;

  constructor(private invoiceService: InvoicesService, private cookieService: CookieService) {
    this.getAllInvoice();
  }
  getAllInvoice() {
    this.search=undefined;
    this.invoiceService.getAllInvoices(JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(
        (patients) => {
          this.invoiceList = patients
        },error=>{alert("Please try Again! Error Occured");}
      );
  }

  confirmDelete(deleteInvoiceId: number) {
    this.deleteId = deleteInvoiceId;
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
    this.deleteInvoiceId(this.deleteId);
    alert('Delete completed');
    let content = document.getElementById('confirmDeleteDisplay');
    content?.classList.remove('active');
  }

  deleteInvoiceId(deleteId: number) {
    this.invoiceService.deleteInvoiceById(JSON.parse(this.cookieService.get('userId')).userToken, deleteId)
      .subscribe(
        (invoice) => {
          this.deleteId = 0;
          this.getAllInvoice();
        },error=>{alert("Failed to delete Invoice");}
      );
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


  searchInvoiceById() {
    this.invoiceList = [];
    this.invoiceService.getInvoiceById(this.search, JSON.parse(this.cookieService.get('userId')).userToken).
      subscribe(data => {
        this.invoiceList = this.invoiceList.concat(data);
      })

  }

}
