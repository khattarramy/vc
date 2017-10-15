import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';


@Component({
  selector: 'app-all-order-distributor-detail',
  templateUrl: './all-order-distributor-detail.component.html',
  styleUrls: ['./all-order-distributor-detail.component.css']
})
export class AllOrderDistributorDetailComponent implements OnInit {
  orderDetails: OrderDetail[];
  id: Number;

  constructor(private orderService: OrderService,
    private orderDetailService: OrderDetailService,
    private route: ActivatedRoute,
    private router: Router) {
  }
  onOrderDetailClick(orderDetail:OrderDetail){
    orderDetail.status="manufacturer";
    orderDetail.quantityDistributor=3;
    this.orderDetailService.updateOrderDetail(orderDetail.orderDetailId,orderDetail).subscribe();
  }

  ngOnInit() {
    this.route.params
      .subscribe(
      (params: Params) => {
        this.id = params['id'];
        this.orderDetailService.getOrderDetailsByOrderAndDistributor(this.id,
          parseInt(localStorage.getItem("userId")))
          .subscribe(response => { this.orderDetails = response; });
      }
      );
  }
}
