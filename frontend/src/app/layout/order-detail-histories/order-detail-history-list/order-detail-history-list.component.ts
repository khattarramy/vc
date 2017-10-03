import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

import { OrderDetailHistory } from '../order-detail-history.model';
import { OrderDetailHistoryService } from '../order-detail-history.service';

@Component({
  selector: 'app-order-detail-history-list',
  templateUrl: './order-detail-history-list.component.html',
  styleUrls: ['./order-detail-history-list.component.css']
})
export class OrderDetailHistoryListComponent implements OnInit, OnDestroy {
  orderDetailHistories: OrderDetailHistory[];
  subscription: Subscription;

  constructor(private orderDetailHistoryService: OrderDetailHistoryService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
        this.subscription = this.orderDetailHistoryService.ordersChanged
      .subscribe(
        (orderDetailHistories: OrderDetailHistory[]) => {
          this.orderDetailHistories = orderDetailHistories;
          
        }
      );
    this.orderDetailHistoryService.getOrdersDetailHistory()
      .subscribe(response => { this.orderDetailHistories = response; });
  }

  onNewOrderDetailHistory() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
