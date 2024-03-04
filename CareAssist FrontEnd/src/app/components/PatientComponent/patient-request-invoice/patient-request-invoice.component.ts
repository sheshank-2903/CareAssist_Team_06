import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HealthCareProvider } from 'src/app/model/HealthCareProvider';
import { Invoices } from 'src/app/model/Invoices';
import { HealthCareProviderService } from 'src/app/services/HealthCareProviderServices/health-care-provider.service';
import { InvoicesService } from 'src/app/services/InvoicesServices/invoices.service';
import { PlansService } from 'src/app/services/PlansServices/plans.service';

@Component({
  selector: 'app-patient-request-invoice',
  templateUrl: './patient-request-invoice.component.html',
  styleUrls: ['./patient-request-invoice.component.css']
})
export class PatientRequestInvoiceComponent {

  currentDate: Date = new Date();
  healthCareProviderList: HealthCareProvider[] = [];
  search!: any;

  currentHealthCareProviderId!: number;

  invoiceDueDate!: Date;
  invoiceTax!: number;
  consultingFees!: number;
  diagnosticTestFees!: number;
  diagnosticScanFees!: number;
  invoiceStatus!: string;

  constructor(private healthCareProviderService: HealthCareProviderService, private invoiceService: InvoicesService, private cookieService: CookieService) {
    this.getHealthCareProviders();
  }

  getHealthCareProviders() {
    this.search=undefined;
    this.healthCareProviderService.getAllHealthCareProvider(JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe(healthCareProviders => this.healthCareProviderList = healthCareProviders,
        error=> alert("Failed to get Health Care Providers"))
  }

  isDueDateInvalid(formValue: any): boolean {
    const invoiceDueDate = new Date(formValue.invoiceDueDate);
    return invoiceDueDate < this.currentDate;
  }

  openInvoiceRequest(healthCareProviderId: number) {
    this.currentHealthCareProviderId = healthCareProviderId;
    let content = document.getElementById('requestInvoiceDisplay');
    content?.classList.add('active');
  }

  submitInvoiceRequest() {
    const invoice: Invoices = {
      "invoiceId": 0,
      "invoiceDate": new Date(),
      "invoiceDueDate": this.invoiceDueDate,
      "patientName": "",
      "patientAddress": "",
      "invoiceTax": this.invoiceTax,
      "consultingFees": this.consultingFees,
      "diagnosticTestFees": this.diagnosticTestFees,
      "diagnosticScanFees": this.diagnosticScanFees,
      "calculatedAmount": 0,
      "invoiceStatus": "",
      "healthCareProviderId": this.currentHealthCareProviderId
    }

    this.invoiceService.addInvoice(invoice, JSON.parse(this.cookieService.get('userId')).userId, JSON.parse(this.cookieService.get('userId')).userToken)
      .subscribe((invoice) => {
        this.invoiceDueDate = new Date();
        this.invoiceTax = 0;
        this.consultingFees = 0;
        this.diagnosticTestFees = 0;
        this.diagnosticScanFees = 0;
        this.invoiceStatus = "";

        alert('Congratulations Invoice request generated');
        let content = document.getElementById('requestInvoiceDisplay');
        content?.classList.remove('active');
      }, (error) => {
        console.error('Error occurred:', error);
        alert('Failed to generate Invoice Request');
      })
  }

  closeInvoiceRequest() {
    let content = document.getElementById('requestInvoiceDisplay');
    content?.classList.remove('active');

  }

  searchHealthCareProviderByName() {
    if (this.search == null || typeof this.search !== 'string') alert("invalid Input for search by name");
    else {
      this.healthCareProviderService.getHealthCareProviderByName(this.search, JSON.parse(this.cookieService.get('userId')).userToken)
        .subscribe((healthCareProviderList) => {
          this.healthCareProviderList = healthCareProviderList;
        })
    }
  }

  searchHealthCareProviderById() {
    const parsedNumber: number = parseInt(this.search, 10);
    if (this.search == null || isNaN(parsedNumber)) alert("invalid Input for search by Id");
    else {
      this.healthCareProviderList = [];
      this.healthCareProviderService.getHealthCareProviderById(JSON.parse(this.cookieService.get('userId')).userToken, this.search)
        .subscribe((healthcareprovider) => {
          this.healthCareProviderList = this.healthCareProviderList.concat(healthcareprovider);
        })
    }
  }
}
