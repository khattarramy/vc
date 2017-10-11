import { OrderDetail } from './../order-detail.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';

import { OrderDetailService } from '../order-detail.service';

@Component({
  selector: 'app-order-detail-edit',
  templateUrl: './order-detail-edit.component.html',
  styleUrls: ['./order-detail-edit.component.css']
})
export class OrderDetailEditComponent implements OnInit {
  id: string;
  editMode = false;

  orderDetail: OrderDetail;
  orderDetailForm: FormGroup = new FormGroup({
    orderDetailId: new FormControl(''),
    orderId: new FormControl(''),
    retailerId: new FormControl(''),
    status: new FormControl(''),
    itemId: new FormControl(''),
    quantity: new FormControl(''),
    quantityDistributor: new FormControl('')
  });
  constructor(private route: ActivatedRoute,
    private orderDetailService: OrderDetailService,
    private router: Router) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
      (params: Params) => {
        this.id = params['id'];
        this.editMode = params['id'] != null;
        this.initForm();
      }
      );
    }

  onSubmit() {
    if (this.editMode) {
      console.log(this.orderDetailForm.value);
      //console.log(this.id);
        this.orderDetailService.updateOrderDetail(this.id, this.orderDetailForm.value)
          .subscribe(x => console.log(x));
    } else {
     // console.log(<Order>this.orderForm.value);
      this.orderDetailService.addOrderDetail(this.orderDetailForm.value)
       .subscribe(x => console.log(x));
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
  private initForm() {

    if (this.editMode) {
      this.orderDetailService.getOrderDetail(this.id)
        .subscribe(response => {

      this.orderDetail = response;
          this.orderDetailForm.setValue({
            orderId: this.orderDetail.orderId,
            retailerId: this.orderDetail.retailerId,
            status: this.orderDetail.status,
            orderDetailId: this.orderDetail.orderDetailId,
            itemId: this.orderDetail.itemId,
            quantity : this.orderDetail.quantity,
            quantityDistributor  : this.orderDetail.quantityDistributor
        });

        });


    }


  }

}


