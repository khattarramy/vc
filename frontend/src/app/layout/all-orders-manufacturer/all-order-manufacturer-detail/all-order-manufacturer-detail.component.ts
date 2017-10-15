import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';


@Component({
  selector: 'app-all-order-manufacturer-detail',
  templateUrl: './all-order-manufacturer-detail.component.html',
  styleUrls: ['./all-order-manufacturer-detail.component.css']
})
export class AllOrderManufacturerDetailComponent implements OnInit {
  order: Order;
  id: Number;

  constructor(private orderService: OrderService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
      (params: Params) => {
        this.id = params['id'];
        this.orderService.getOrder(this.id)
          .subscribe(response => { this.order = response; });
      }
      );
  }


  onEditOrder() {
    this.router.navigate(['edit'], { relativeTo: this.route });
    // this.router.navigate(['../', this.id, 'edit'], {relativeTo: this.route});
  }

  onDeleteOrder() {
    this.orderService.deleteOrder(this.id)
      .subscribe(x => console.log(x));  ;
    this.router.navigate(['/orders']);
  }

}
