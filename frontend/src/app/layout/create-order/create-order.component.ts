import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { Item } from 'app/layout/items/item.model';
import { ItemService } from 'app/layout/items/item.service';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';


@Component({
  selector: 'create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {
  id: string;
  editMode = false;
  items: Item[];
  orderDetail: OrderDetail;
  order:Order = new Order();
  createOrderForm: FormGroup = new FormGroup({
    quantity: new FormControl(''),
    itemId: new FormControl('')
  });
  constructor(private route: ActivatedRoute,
    private orderDetailService: OrderDetailService,
    private itemService: ItemService,
    private orderService: OrderService,
    private router: Router) {
  }

  ngOnInit() {
    this.initForm();
    this.itemService.getItems()
      .subscribe(response => { this.items = response; });

  }

  onSubmit() {
    this.orderDetail = this.createOrderForm.value;

    this.orderDetail.status = "Cart";
    this.orderDetail.retailerId = localStorage.getItem('userId');
    this.orderDetail.quantityDistributor = 0;


    this.orderService.getOrdersByStatusAndRetailer("Cart", localStorage.getItem("userId"))
      .subscribe(response => {
        if (response.length > 0) {
          this.orderDetail.orderId = response[0].orderId;
          this.orderDetailService.addOrderDetail(this.orderDetail)
            .subscribe(x => console.log(x));
        }
        else{
        this.order.userId = localStorage.getItem("userId");
        this.order.status = "Cart";
        this.order.dateInitialized = "2016-03-03T08:00:00+02:00";
        this.order.dateFinished = "2016-03-03T08:00:00+02:00";
          this.orderService.addOrder(this.order)
          .subscribe(response => {
            this.orderDetail.orderId = response.orderId;
            this.orderDetailService.addOrderDetail(this.orderDetail)
              .subscribe(x => console.log(x));

          });

        }

      });

  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
  private initForm() {


  }

}


