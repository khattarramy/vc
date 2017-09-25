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
  orderDetails: OrderDetail[];
  subscription: Subscription;

  constructor(private orderDetailService: OrderDetailService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
        this.subscription = this.orderDetailService.ordersChanged
      .subscribe(
        (orderDetails: OrderDetail[]) => {
          this.orderDetails = orderDetails;
        }
      );
    this.orderDetailService.getOrderDetails()
      .subscribe(response => { this.orderDetails = response; });
  }

  onNewOrderDetail() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
