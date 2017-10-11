import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';

@Component({
  selector: 'cart-detail-detail',
  templateUrl: './cart-detail.component.html',
  styleUrls: ['./cart-detail.component.css']
})
export class CartDetailComponent implements OnInit {
  orderDetail: OrderDetail;
  id: Number;

  constructor(private orderDetailService: OrderDetailService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
      (params: Params) => {
        this.id = params['id'];
        this.orderDetailService.getOrderDetail(this.id)
          .subscribe(response => { this.orderDetail = response; });
      }
      );
  }


  onEditOrderDetail() {
    this.router.navigate(['edit'], { relativeTo: this.route });
    // this.router.navigate(['../', this.id, 'edit'], {relativeTo: this.route});
  }

  onDeleteOrderDetail() {
    this.orderDetailService.deleteOrderDetail(this.id)
      .subscribe(x => console.log(x));  ;
    this.router.navigate(['/cart']);
  }

}
