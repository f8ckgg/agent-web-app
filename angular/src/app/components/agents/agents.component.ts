import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AgentService } from '../../services/agent.service';
import { AgentDTO } from '../../interfaces';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {
  agents: AgentDTO[] = [];


  constructor(
    private agentService: AgentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllAgents();
  }

  viewAgentDetails(agentId: number) {
    this.router.navigate(['/agent', agentId]);
  }

  getAllAgents(): void {
    this.agentService.getAllAgents().subscribe(agents => {
      this.agents = agents;
    });
  }

  createAgent(): void {
    this.router.navigate(['/agent/create']);
  }
}

