import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AgentService} from "../../services/agent.service";
import {AgentDTO} from "../../interfaces";
import {RatingService} from "../../services/rating.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-agent',
  templateUrl: './agent.component.html',
  styleUrls: ['./agent.component.css']
})
export class AgentComponent implements OnInit {
  agentForm: FormGroup;
  agentId: number;
  averageRating$: Observable<number> | undefined;
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private agentService: AgentService,
    private router: Router,
    private readonly ratingService: RatingService
  ) {
    this.agentId = Number(this.route.snapshot.paramMap.get('id'));
    this.agentForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      licenseNumber: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    if (this.agentId) {
      this.agentService.getAgentById(this.agentId).subscribe((agent: AgentDTO) => {
        this.agentForm.patchValue({
          name: agent.name,
          email: agent.email,
          licenseNumber: agent.licenseNumber
        });
      });
      this.averageRating$ = this.ratingService.getAverageRating(this.agentId );
    }
  }

  onSubmit(): void {
    const agentPojo = this.agentForm.value;
    if (this.agentId) {
      this.agentService.updateAgent(this.agentId, agentPojo).subscribe(() => {
        this.router.navigate(['/agents']);
      });
    } else {
      this.agentService.createAgent(agentPojo).subscribe(() => {
        this.router.navigate(['/agents']);
      });
    }
  }

  onDelete(): void {
    this.agentService.deleteAgent(this.agentId).subscribe(() => {
      this.router.navigate(['/agents']);
    });
  }
}
