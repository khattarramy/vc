import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { OrderDetailDto } from 'app/layout/order-details/order-detail-dto.model';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-all-order-distributor-detail',
  templateUrl: './all-order-distributor-detail.component.html',
  styleUrls: ['./all-order-distributor-detail.component.css']
})
export class AllOrderDistributorDetailComponent implements OnInit, OnDestroy {
  orderDetails: OrderDetailDto[];
  id: Number;
  orderDetail:OrderDetail;
  subscription: Subscription;
  
  orderDetailForm: FormGroup = new FormGroup({
    quantityDistributor: new FormControl(''),
  });
  constructor(private orderService: OrderService,
    private orderDetailService: OrderDetailService,
    private route: ActivatedRoute,
    private router: Router) {
  }
  onOrderDetailClick(){
    this.orderDetail.status="manufacturer";
    this.orderDetail.quantityDistributor=this.orderDetailForm.value.quantityDistributor;
    this.orderDetailService.updateOrderDetail(this.orderDetail.orderDetailId,this.orderDetail,"getOrderDetailsByOrderAndDistributor",[this.id,
      parseInt(localStorage.getItem("userId"))]).subscribe();
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
        this.orderDetailService.getOrderDetailsByOrderAndDistributor(this.id,
          parseInt(localStorage.getItem("userId")))
          .subscribe(response => { this.orderDetails = response; });
      }
      );


  }

  initForm(orderDetail:OrderDetail){
  this.orderDetail = orderDetail;    
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
