import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {DealDTO} from "../../interfaces";
import {DealService} from "../../services/deal.service";

@Component({
  selector: 'app-admin-deal',
  templateUrl: './admin-deal.component.html',
  styleUrls: ['./admin-deal.component.css']
})
export class AdminDealComponent implements OnInit {
  dataSource!: MatTableDataSource<DealDTO>;
  displayedColumns: string[] = ['buyerName', 'buyerEmail', 'propertyAddress', 'propertyPrice', 'agentName', 'agentEmail', 'status', 'approve', 'reject'];

  constructor(private dealService: DealService) {}

  ngOnInit(): void {
    this.dealService.getAllDeals().subscribe((deals: DealDTO[]) => {
      this.dataSource = new MatTableDataSource<DealDTO>(deals);
    });
  }

  approveDeal(id: number) {
    this.dealService.approveDeal(id).subscribe((deal: DealDTO) => {
      const index = this.dataSource.data.findIndex((d: DealDTO) => d.id === deal.id);
      this.dataSource.data[index] = deal;
      this.dataSource._updateChangeSubscription();
    });
  }

  rejectDeal(id: number) {
    this.dealService.rejectDeal(id).subscribe((deal: DealDTO) => {
      const index = this.dataSource.data.findIndex((d: DealDTO) => d.id === deal.id);
      this.dataSource.data[index] = deal;
      this.dataSource._updateChangeSubscription();
    });
  }
}

