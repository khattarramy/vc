import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Order } from 'app/layout/orders/order.model';
import { OrderService } from 'app/layout/orders/order.service';


@Component({
  selector: 'app-all-order-list',
  templateUrl: './all-order-list.component.html',
  styleUrls: ['./all-order-list.component.css']
})
export class AllOrderListComponent implements OnInit {
  orders: Order[];
  subscription: Subscription;

  constructor(private orderService: OrderService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.orderService.getOrdersByRetailer(parseInt(localStorage.getItem("userId")))
      .subscribe(response => { this.orders = response; });
  }

  onNewOrder() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

}
