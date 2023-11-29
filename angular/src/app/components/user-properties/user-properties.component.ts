import { Component } from '@angular/core';
import {DealDTO, PropertyDTO} from "../../interfaces";
import {PropertyService} from "../../services/property.service";
import {Router} from "@angular/router";
import {DealService} from "../../services/deal.service";

@Component({
  selector: 'app-user-properties',
  templateUrl: './user-properties.component.html',
  styleUrls: ['./user-properties.component.css']
})
export class UserPropertiesComponent {
  properties: PropertyDTO[] = [];

  constructor(private propertyService: PropertyService, private router: Router, private dealService: DealService) { }

  ngOnInit(): void {
    this.getAllProperties();
  }


  createDeal(property: PropertyDTO): void {
    const deal: DealDTO = {
      id: null,
      buyer: null ,
      property: property,
      status: "PENDING"
    };
    this.dealService.createDeal(deal).subscribe(createdDeal => {
      this.router.navigate(['/userdeals']);
    });
  }


  getAllProperties(): void {
    this.propertyService. findAllByAvailableTrue().subscribe(properties => {
      this.properties = properties;
    });
  }

}
