import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {DealDTO} from "../../interfaces";
import {DealService} from "../../services/deal.service";

@Component({
  selector: 'app-user-deal',
  templateUrl: './user-deal.component.html',
  styleUrls: ['./user-deal.component.css']
})
export class UserDealComponent implements OnInit{
  dataSource!: MatTableDataSource<DealDTO>;
  displayedColumns: string[] = ['propertyAddress', 'propertyPrice', 'agentName', 'agentEmail', 'status','actions'];

  constructor(private dealService: DealService) {}

  ngOnInit(): void {
    this.dealService.getAllDealsByBuyerId().subscribe((deals: DealDTO[]) => {
      this.dataSource = new MatTableDataSource<DealDTO>(deals);
    });
  }
}
