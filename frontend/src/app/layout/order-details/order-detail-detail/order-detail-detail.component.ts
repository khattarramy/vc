import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { OrderDetail } from '../order-detail.model';
import { OrderDetailService } from '../order-detail.service';

@Component({
  selector: 'app-order-detail-detail',
  templateUrl: './order-detail-detail.component.html',
  styleUrls: ['./order-detail-detail.component.css']
})
export class OrderDetailDetailComponent implements OnInit {
  orderDetail: OrderDetail;
  id: string;

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
    this.router.navigate(['/orderdetails']);
  }

}
