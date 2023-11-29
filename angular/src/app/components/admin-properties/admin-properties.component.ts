import {Component, OnInit} from '@angular/core';
import {PropertyDTO} from "../../interfaces";
import {PropertyService} from "../../services/property.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-properties',
  templateUrl: './admin-properties.component.html',
  styleUrls: ['./admin-properties.component.css']
})
export class AdminPropertiesComponent implements OnInit {
  properties: PropertyDTO[] = [];

  constructor(private propertyService: PropertyService, private router: Router) { }

  ngOnInit(): void {
    this.getAllProperties();
  }

  createProperty(): void {
    this.router.navigate(['/adminproperty']);
  }

  viewPropertyDetails(propertyId: number) {
    this.router.navigate(['/adminproperty', propertyId]);
  }

  getAllProperties(): void {
    this.propertyService.getAllProperties().subscribe(properties => {
      this.properties = properties;
    });
  }

}
