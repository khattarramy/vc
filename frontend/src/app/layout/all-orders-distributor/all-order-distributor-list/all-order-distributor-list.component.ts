
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Order } from 'app/layout/orders/order.model';
import { OrderService } from 'app/layout/orders/order.service';


@Component({
  selector: 'app-all-order-distributor-list',
  templateUrl: './all-order-distributor-list.component.html',
  styleUrls: ['./all-order-distributor-list.component.css']
})
export class AllOrderDistributorListComponent implements OnInit, OnDestroy {
  orders: Order[];
  subscription: Subscription;

  constructor(private orderService: OrderService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
        this.subscription = this.orderService.ordersChanged
      .subscribe(
        (orders: Order[]) => {
          this.orders = orders;
        }
      );
    this.orderService.getOrders()
      .subscribe(response => { this.orders = response; });
  }

  onNewOrder() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
