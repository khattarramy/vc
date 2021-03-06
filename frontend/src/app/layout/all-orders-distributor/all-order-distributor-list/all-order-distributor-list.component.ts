import { Pipe, PipeTransform } from '@angular/core';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Order } from 'app/layout/orders/order.model';
import { OrderService } from 'app/layout/orders/order.service';
import { OrderDto } from 'app/layout/orders/order-dto.model';


@Component({
  selector: 'app-all-order-distributor-list',
  templateUrl: './all-order-distributor-list.component.html',
  styleUrls: ['./all-order-distributor-list.component.css']
})
export class AllOrderDistributorListComponent implements OnInit,OnDestroy {
  orders: OrderDto[];
  subscription: Subscription;

  constructor(private orderService: OrderService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {

    this.subscription = this.orderService.ordersChanged
    .subscribe(
      (orders: OrderDto[]) => {
        this.orders = orders;
      }
    );


    this.orderService.getOrdersByDistributor(parseInt(localStorage.getItem("userId")))
    .subscribe(response => { this.orders = response; });
    }

  onNewOrder() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
