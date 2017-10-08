import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';



@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit, OnDestroy {
  orderDetails: OrderDetail[];
  
  constructor(private orderDetailService: OrderDetailService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
        
    this.orderDetailService.getOrderDetailsByStatusAndRetailer("Cart",localStorage.getItem("userId"))
      .subscribe(response => { this.orderDetails = response; });
  }

  onNewOrderDetail() {
    for(var i = 0;i<this.orderDetails.length;i++) { 
      this.orderDetails[i].status = "Sent";
      this.orderDetailService.updateOrderDetail(this.orderDetails[i].orderDetailId, this.orderDetails[i])
      .subscribe(x => console.log(x));
     
   } 
   this.router.navigate(['/cart']);
   
  }

  ngOnDestroy() {

  }
}
