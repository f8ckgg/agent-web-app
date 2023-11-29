import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PropertyService} from "../../services/property.service";
import {AgentService} from "../../services/agent.service";
import {AgentDTO} from "../../interfaces";

@Component({
  selector: 'app-admin-property',
  templateUrl: './admin-property.component.html',
  styleUrls: ['./admin-property.component.css']
})
export class AdminPropertyComponent {
  propertyForm: FormGroup;
  propertyId: number;
  agents: AgentDTO[]=[];
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private propertyService: PropertyService,
    private router: Router,
    private agentService: AgentService
  ) {
    this.propertyId = Number(this.route.snapshot.paramMap.get('id'));
    this.propertyForm = this.fb.group({
      address: [''],
      price: [''],
      description: [''],
      available: true,
      agent: ['']
    });
  }

  ngOnInit(): void {
    this.agentService.getAllAgents().subscribe((agents: AgentDTO[]) => {
      this.agents = agents;
    });
    if (this.propertyId) {
      this.propertyService.getPropertyById(this.propertyId).subscribe((property) => {
        this.propertyForm.patchValue(property);
      });
    }
  }

  onSubmit(): void {
    const propertyPojo = this.propertyForm.value;
    propertyPojo.available = propertyPojo.available === 'true'; // transform the string value to a boolean
    const agentId = propertyPojo.agent;
    const agent: AgentDTO = { id: agentId, name: '', email:'',licenseNumber:''};
    propertyPojo.agent = agent;
    if (this.propertyId) {
      this.propertyService.updateProperty(this.propertyId, propertyPojo).subscribe(() => {
        this.router.navigate(['/adminproperties']);
      });
    } else {
      this.propertyService.createProperty(propertyPojo).subscribe(() => {
        this.router.navigate(['/adminproperties']);
      });
    }
  }

  onDelete(): void {
    this.propertyService.deleteProperty(this.propertyId).subscribe(() => {
      this.router.navigate(['/adminproperties']);
    });
  }


}
