import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Invoices } from 'src/app/model/Invoices';

@Injectable({
  providedIn: 'root'
})
export class InvoicesService {

  constructor(private _http:HttpClient) { }

  baseUrl:string = 'http://localhost:8080/api/v1/invoice/'

  addInvoice(body:Invoices,patientId:number,token:string):Observable<Invoices>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.post<Invoices>(this.baseUrl+`add/${patientId}`,body,{headers,responseType: 'json'});
  }

  getAllInvoices(token:string):Observable<Invoices[]>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.get<Invoices[]>(this.baseUrl+"getAll", { headers, responseType: 'json' });
  }

  getInvoiceById(invoiceId:number,token:string):Observable<Invoices>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.get<Invoices>(this.baseUrl+`get/${invoiceId}`,{ headers, responseType: 'json' });
  }

  getInvoiceByPatientId(patientId:number,token:string):Observable<Invoices[]>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.get<Invoices[]>(this.baseUrl+`getByPatientId/${patientId}`,{headers,responseType: 'json'});
  }

  getInvoiceByHealthCareProviderId(token:string,healthCareProviderId:number):Observable<Invoices[]>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.get<Invoices[]>(this.baseUrl+`getByHealthCareProviderId/${healthCareProviderId}`, { headers, responseType: 'json' });
  }

  updateInvoiceStatus(token:string,invoiceId:number, invoiceStatus:string){
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.put<Invoices>(this.baseUrl+`updateInvoiceStatus/${invoiceId}/${invoiceStatus}`,{},{ headers, responseType: 'json' });
  }

  deleteInvoiceById(token:string,invoiceId:number):Observable<boolean>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.delete<boolean>(this.baseUrl+`delete/${invoiceId}`,{ headers, responseType: 'json' });
  }
}

  
