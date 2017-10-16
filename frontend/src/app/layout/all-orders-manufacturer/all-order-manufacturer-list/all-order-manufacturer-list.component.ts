
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Order } from 'app/layout/orders/order.model';
import { OrderService } from 'app/layout/orders/order.service';
import { OrderDto } from 'app/layout/orders/order-dto.model';


@Component({
  selector: 'app-all-order-manufacturer-list',
  templateUrl: './all-order-manufacturer-list.component.html',
  styleUrls: ['./all-order-manufacturer-list.component.css']
})
export class AllOrderManufacturerListComponent implements OnInit {
  orders: OrderDto[];
  subscription: Subscription;

  constructor(private orderService: OrderService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.orderService.getOrdersByManufacturer(parseInt(localStorage.getItem("userId")))
    .subscribe(response => { this.orders = response; });
  }

}
