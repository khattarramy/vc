import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';
import { Subscription } from 'rxjs';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderDetailDto } from 'app/layout/order-details/order-detail-dto.model';


@Component({
  selector: 'app-all-order-detail',
  templateUrl: './all-order-detail.component.html',
  styleUrls: ['./all-order-detail.component.css']
})
export class AllOrderDetailComponent implements OnInit {
  order: Order;
  id: Number;
  subscription: Subscription;  
  orderDetails: OrderDetailDto[];
  
  constructor(
    private orderDetailService: OrderDetailService,
    private orderService: OrderService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
      (params: Params) => {
        this.id = params['id'];
        this.subscription = this.orderDetailService.ordersChanged
        .subscribe(
          (orderDetails: OrderDetailDto[]) => {
            this.orderDetails = orderDetails;
          }
        );
        this.orderDetailService.getOrderDetailsByOrder(this.id)
          .subscribe(response => { this.orderDetails = response; });
      }
      );
  }


}
