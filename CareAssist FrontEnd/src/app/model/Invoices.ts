export interface Invoices{

  invoiceId:number;
	
  invoiceDate:Date;
	
  invoiceDueDate:Date;
	
  patientName:string; 
	
  patientAddress:string;
	
  invoiceTax:number;
	
  consultingFees:number;
	
  diagnosticTestFees:number;
	
  diagnosticScanFees:number;
	
  calculatedAmount:number;
	
  invoiceStatus:string;
	
  healthCareProviderId:number;
}