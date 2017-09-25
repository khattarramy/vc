import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

import { OrderDetail } from '../order-detail.model';
import { OrderDetailService } from '../order-detail.service';

@Component({
  selector: 'app-order-detail-list',
  templateUrl: './order-detail-list.component.html',
  styleUrls: ['./order-detail-list.component.css']
})
export class OrderDetailListComponent implements OnInit, OnDestroy {
  orders: OrderDetail[];
  subscription: Subscription;

  constructor(private orderDetailService: OrderDetailService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
        this.subscription = this.orderDetailService.ordersChanged
      .subscribe(
        (orders: OrderDetail[]) => {
          this.orders = orders;
        }
      );
    this.orderDetailService.getOrders()
      .subscribe(response => { this.orders = response; });
  }

  onNewOrder() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
