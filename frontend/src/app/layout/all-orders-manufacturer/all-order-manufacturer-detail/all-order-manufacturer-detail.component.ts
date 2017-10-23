import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';
import { OrderDetailDto } from 'app/layout/order-details/order-detail-dto.model';
import { Subscription } from 'rxjs';
import { FormGroup, Validators, FormControl } from '@angular/forms';


@Component({
  selector: 'app-all-order-manufacturer-detail',
  templateUrl: './all-order-manufacturer-detail.component.html',
  styleUrls: ['./all-order-manufacturer-detail.component.css']
})
export class AllOrderManufacturerDetailComponent implements OnInit , OnDestroy {
  orderDetails: OrderDetailDto[];
  id: Number;
  subscription: Subscription;
  selectedRow : Number;
  orderDetail : OrderDetail;
  
  constructor(
    private orderDetailService: OrderDetailService,
    private orderService: OrderService,
    private route: ActivatedRoute,
    private router: Router) {
  }
  orderDetailForm: FormGroup = new FormGroup({
    enableButton: new FormControl('',Validators.required),
  });
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
        this.orderDetailService.getOrderDetailsByOrderAndManufacturerAndStatus(this.id,
          parseInt(localStorage.getItem("userId")),"manufacturer")
          .subscribe(response => { this.orderDetails = response; });
      }
      );
  }

  onOrderDetailClick(orderDetail:OrderDetail,index){
   this.selectedRow = index;
   this.orderDetail = orderDetail;
   this.orderDetailForm.setValue({
    enableButton: 1
  });
  }

  onSave(){
    this.orderDetail.status="distributor";
    this.orderDetailService.updateOrderDetail(this.orderDetail.orderDetailId,this.orderDetail,"getOrderDetailsByOrderAndManufacturerAndStatus",[this.id,
      parseInt(localStorage.getItem("userId")),"manufacturer"]).subscribe();
  

  }

  onCancel(){
    this.orderDetailForm.setValue({
      enableButton: ""
    });
    this.selectedRow = null;

  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
