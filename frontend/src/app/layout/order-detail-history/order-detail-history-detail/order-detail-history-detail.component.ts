import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { OrderDetailHistory } from '../order-detail-history.model';
import { OrderDetailHistoryService } from '../order-detail-history.service';

@Component({
  selector: 'app-order-detail-history-detail',
  templateUrl: './order-detail-history-detail.component.html',
  styleUrls: ['./order-detail-history-detail.component.css']
})
export class OrderDetailHistoryDetailComponent implements OnInit {
  orderDetailHistory: OrderDetailHistory;
  id: string;

  constructor(private orderDetailHistoryService: OrderDetailHistoryService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
      (params: Params) => {
        this.id = params['id'];
        this.orderDetailHistoryService.getOrderDetailHistory(this.id)
          .subscribe(response => { this.orderDetailHistory = response; });
      }
      );
  }


  onEditOrderDetailHistory() {
    this.router.navigate(['edit'], { relativeTo: this.route });
    // this.router.navigate(['../', this.id, 'edit'], {relativeTo: this.route});
  }

  onDeleteOrderDetailHistory() {
    this.orderDetailHistoryService.deleteOrderDetailHistory(this.id)
      .subscribe(x => console.log(x));  ;
    this.router.navigate(['/order-details']);
  }

}
