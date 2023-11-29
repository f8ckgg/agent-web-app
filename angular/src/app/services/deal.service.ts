import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {DealDTO} from "../interfaces";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DealService {
  private baseUrl = environment.apiUrl+'/api/deals';

  constructor(private http: HttpClient) { }

  getAllDeals(): Observable<DealDTO[]> {
    return this.http.get<DealDTO[]>(`${this.baseUrl}`);
  }

  getDealById(id: number): Observable<DealDTO> {
    return this.http.get<DealDTO>(`${this.baseUrl}/${id}`);
  }

  createDeal(deal: DealDTO): Observable<DealDTO> {
    return this.http.post<DealDTO>(`${this.baseUrl}`, deal);
  }

  updateDeal(id: number, deal: DealDTO): Observable<DealDTO> {
    return this.http.put<DealDTO>(`${this.baseUrl}/${id}`, deal);
  }

  deleteDeal(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  getAllDealsByBuyerId(): Observable<DealDTO[]> {
    return this.http.get<DealDTO[]>(`${this.baseUrl}/byBuyerId`);
  }

  approveDeal(id: number): Observable<DealDTO> {
    return this.http.put<DealDTO>(`${this.baseUrl}/approve/${id}`, null);
  }

  rejectDeal(id: number): Observable<DealDTO> {
    return this.http.put<DealDTO>(`${this.baseUrl}/reject/${id}`, null);
  }
}
