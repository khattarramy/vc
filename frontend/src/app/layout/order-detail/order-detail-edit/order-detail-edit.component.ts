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
  orderForm: FormGroup = new FormGroup({
    orderDetailId: new FormControl(''),
    orderId: new FormControl(''),
    userId: new FormControl(''),
    statusId: new FormControl(''),
    itemId: new FormControl('')
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
      console.log(this.orderForm.value);
      //console.log(this.id);
        this.orderDetailService.updateOrder(this.id, this.orderForm.value)
          .subscribe(x => console.log(x));
    } else {
     // console.log(<Order>this.orderForm.value);
      this.orderDetailService.addOrder(this.orderForm.value)
       .subscribe(x => console.log(x));
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
  private initForm() {

    if (this.editMode) {
      this.orderDetailService.getOrder(this.id)
        .subscribe(response => {

      this.orderDetail = response;
          this.orderForm.setValue({
            orderId: this.orderDetail.orderId,
            userId: this.orderDetail.userId,
            statusId: this.orderDetail.statusId,
            orderDetailId: this.orderDetail.orderDetailId,
            itemId: this.orderDetail.itemId
        });

        });


    }


  }

}


