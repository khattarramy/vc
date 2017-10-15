import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { Order } from 'app/layout/orders/order.model';
import { OrderService } from 'app/layout/orders/order.service';


@Component({
  selector: 'app-all-order-manufacturer-edit',
  templateUrl: './all-order-manufacturer-edit.component.html',
  styleUrls: ['./all-order-manufacturer-edit.component.css']
})
export class AllOrderManufacturerEditComponent implements OnInit {
  id: Number;
  editMode = false;

  order: Order;
  orderForm: FormGroup = new FormGroup({
    orderId: new FormControl(''),
    userId: new FormControl(''),
    status: new FormControl(''),
    dateInitialized: new FormControl(''),
    dateFinished: new FormControl('')
  });
  constructor(private route: ActivatedRoute,
    private orderService: OrderService,
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
        this.orderService.updateOrder(this.id, this.orderForm.value)
          .subscribe(x => console.log(x));
    } else {
     // console.log(<Order>this.orderForm.value);
      this.orderService.addOrder(this.orderForm.value)
       .subscribe(x => console.log(x));
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
  private initForm() {

    if (this.editMode) {
      this.orderService.getOrder(this.id)
        .subscribe(response => {

      this.order = response;
          this.orderForm.setValue({
            orderId: this.order.orderId,
            userId: this.order.userId,
            status: this.order.status,
            dateInitialized: this.order.dateInitialized,
            dateFinished: this.order.dateFinished
        });

        });


    }


  }

}


