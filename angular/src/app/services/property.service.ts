import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {PropertyDTO} from "../interfaces";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PropertyService {
  private baseUrl = environment.apiUrl+'/api/properties';

  constructor(private http: HttpClient) {}

  getAllProperties(): Observable<PropertyDTO[]> {
    return this.http.get<PropertyDTO[]>(this.baseUrl);
  }

  getPropertyById(id: number): Observable<PropertyDTO> {
    return this.http.get<PropertyDTO>(`${this.baseUrl}/${id}`);
  }

  createProperty(propertyDTO: PropertyDTO): Observable<PropertyDTO> {
    return this.http.post<PropertyDTO>(this.baseUrl, propertyDTO);
  }

  updateProperty(id: number, propertyDTO: PropertyDTO): Observable<PropertyDTO> {
    return this.http.put<PropertyDTO>(`${this.baseUrl}/${id}`, propertyDTO);
  }

  deleteProperty(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  findAllByAvailableTrue(): Observable<PropertyDTO[]> {
    return this.http.get<PropertyDTO[]>(`${this.baseUrl}/available`);
  }
}
