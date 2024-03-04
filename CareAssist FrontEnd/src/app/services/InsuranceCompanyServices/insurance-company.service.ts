import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InsuranceCompany } from 'src/app/model/InsuranceCompany';

@Injectable({
  providedIn: 'root'
})
export class InsuranceCompanyService {

  constructor(private _http:HttpClient) { }

  baseUrl:string = 'http://localhost:8080/api/v1/insurancecompany/'

  addInsuranceCompany(body:InsuranceCompany):Observable<InsuranceCompany>{
    return this._http.post<InsuranceCompany>(this.baseUrl+"register",body)
  }

  updateInsuranceCompany(body:InsuranceCompany,token:string):Observable<InsuranceCompany>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.put<InsuranceCompany>(this.baseUrl+"update",body,{ headers, responseType: 'json' })
  }

  getInsuranceCompanyById(InsuranceCompanyId:number,token:string):Observable<InsuranceCompany>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.get<InsuranceCompany>(this.baseUrl+`get/${InsuranceCompanyId}`,{ headers, responseType: 'json' })
  }

  getAllInsuranceCompany(token:string):Observable<InsuranceCompany[]>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.get<InsuranceCompany[]>(this.baseUrl+`getAll`, { headers, responseType: 'json' })
  }

  deleteInsuranceCompanyById(token:string,insuranceCompanyId:number):Observable<boolean>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.delete<boolean>(this.baseUrl+`delete/${insuranceCompanyId}`,{ headers, responseType: 'json' })
  }

  getInsuranceCompanyByName(token:string,insuranceCompanyName:string):Observable<InsuranceCompany[]>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);
    return this._http.get<InsuranceCompany[]>(this.baseUrl+`getByName/${insuranceCompanyName}`,{ headers, responseType: 'json' })
  }
}
