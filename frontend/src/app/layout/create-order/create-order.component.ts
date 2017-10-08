import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';


@Component({
  selector: 'create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {
  id: string;
  editMode = false;

  orderDetail: OrderDetail;
  createOrderForm: FormGroup = new FormGroup({
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
      console.log(this.createOrderForm.value);
      //console.log(this.id);
        this.orderDetailService.updateOrderDetail(this.id, this.createOrderForm.value)
          .subscribe(x => console.log(x));
    } else {
     // console.log(<Order>this.orderForm.value);
      this.orderDetailService.addOrderDetail(this.createOrderForm.value)
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
          this.createOrderForm.setValue({
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


