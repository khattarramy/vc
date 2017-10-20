import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';
import { OrderDetailDto } from 'app/layout/order-details/order-detail-dto.model';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-all-order-manufacturer-detail',
  templateUrl: './all-order-manufacturer-detail.component.html',
  styleUrls: ['./all-order-manufacturer-detail.component.css']
})
export class AllOrderManufacturerDetailComponent implements OnInit , OnDestroy {
  orderDetails: OrderDetailDto[];
  id: Number;
  subscription: Subscription;
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
        this.orderDetailService.getOrderDetailsByOrderAndManufacturer(this.id,
          parseInt(localStorage.getItem("userId")))
          .subscribe(response => { this.orderDetails = response; });
      }
      );
  }

  onOrderDetailClick(orderDetail:OrderDetail){
    orderDetail.status="distributor";
    this.orderDetailService.updateOrderDetail(orderDetail.orderDetailId,orderDetail,"getOrderDetailsByOrderAndManufacturer",[this.id,
      parseInt(localStorage.getItem("userId"))]).subscribe();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
