import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';



@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit, OnDestroy {
  orderDetails: OrderDetail[];
  order : Order;
  
  constructor(private orderDetailService: OrderDetailService,
    private orderService: OrderService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
        
    this.orderDetailService.getOrderDetailsByStatusAndRetailer("cart",parseInt(localStorage.getItem("userId")))
      .subscribe(response => { this.orderDetails = response; });
  }

  onNewOrderDetail() {
    for(var i = 0;i<this.orderDetails.length;i++) { 
      this.orderDetails[i].status = "sent";
      this.orderDetailService.updateOrderDetail(this.orderDetails[i].orderDetailId, this.orderDetails[i])
      .subscribe(x => console.log(x));
     
   } 

   this.orderService.getOrdersByStatusAndRetailer("cart", parseInt(localStorage.getItem("userId")))
   .subscribe(response => {
     if (response.length > 0) {
       this.order = response[0];
       this.order.status="sent";
       this.orderService.updateOrder(this.order.orderId,this.order)
         .subscribe(x => console.log(x));
     }
   });
   this.router.navigate(['/create-order']);
   
  }

  ngOnDestroy() {

  }
}
