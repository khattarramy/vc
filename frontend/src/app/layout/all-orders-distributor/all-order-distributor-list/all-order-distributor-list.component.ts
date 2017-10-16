
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
export class AllOrderDistributorListComponent implements OnInit {
  orders: OrderDto[];
  subscription: Subscription;

  constructor(private orderService: OrderService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {

    this.orderService.getOrdersByDistributor(parseInt(localStorage.getItem("userId")))
    .subscribe(response => { this.orders = response; });
    }

  onNewOrder() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

}
