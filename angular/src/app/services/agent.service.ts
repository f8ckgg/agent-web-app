import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AgentDTO} from "../interfaces";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class AgentService {
  private readonly API_URL = environment.apiUrl+'/api/agents';

  constructor(private http: HttpClient) {}

  getAllAgents(): Observable<AgentDTO[]> {
    return this.http.get<AgentDTO[]>(this.API_URL);
  }

  getAgentById(id: number): Observable<AgentDTO> {
    return this.http.get<AgentDTO>(`${this.API_URL}/${id}`);
  }

  createAgent(agentDTO: AgentDTO): Observable<AgentDTO> {
    return this.http.post<AgentDTO>(this.API_URL, agentDTO);
  }

  updateAgent(id: number, agentDTO: AgentDTO): Observable<AgentDTO> {
    return this.http.put<AgentDTO>(`${this.API_URL}/${id}`, agentDTO);
  }

  deleteAgent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }

}
