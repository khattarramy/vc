import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { Item } from 'app/layout/items/item.model';
import { ItemService } from 'app/layout/items/item.service';


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
  createOrderForm: FormGroup = new FormGroup({
    quantity: new FormControl(''),
    itemId: new FormControl('')
  });
  constructor(private route: ActivatedRoute,
    private orderDetailService: OrderDetailService,
    private itemService: ItemService,
    private router: Router) {
  }

  ngOnInit() {
    this.initForm();
    this.itemService.getItems()
      .subscribe(response => { this.items = response; });
  }

  onSubmit() {
    this.orderDetail = this.createOrderForm.value;
    this.orderDetail.orderId = "111";
    this.orderDetail.statusId = "Cart";
    this.orderDetail.retailerId = localStorage.getItem('userId');

    this.orderDetail.quantityDistributor = 0;
    this.orderDetailService.addOrderDetail(this.orderDetail)
      .subscribe(x => console.log(x));

    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
  private initForm() {


  }

}


