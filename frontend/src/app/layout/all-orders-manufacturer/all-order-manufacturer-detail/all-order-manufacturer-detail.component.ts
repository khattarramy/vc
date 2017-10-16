import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';
import { OrderDetailDto } from 'app/layout/order-details/order-detail-dto.model';


@Component({
  selector: 'app-all-order-manufacturer-detail',
  templateUrl: './all-order-manufacturer-detail.component.html',
  styleUrls: ['./all-order-manufacturer-detail.component.css']
})
export class AllOrderManufacturerDetailComponent implements OnInit {
  orderDetails: OrderDetailDto[];
  id: Number;

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
        this.orderDetailService.getOrderDetailsByOrderAndManufacturer(this.id,
          parseInt(localStorage.getItem("userId")))
          .subscribe(response => { this.orderDetails = response; });
      }
      );
  }

  onOrderDetailClick(orderDetail:OrderDetail){
    orderDetail.status="distributor";
    this.orderDetailService.updateOrderDetail(orderDetail.orderDetailId,orderDetail).subscribe();
  }



}
