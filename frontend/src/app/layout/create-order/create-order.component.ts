import { Component, OnInit, Renderer } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { OrderDetail } from 'app/layout/order-details/order-detail.model';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { Item } from 'app/layout/items/item.model';
import { ItemService } from 'app/layout/items/item.service';
import { OrderService } from 'app/layout/orders/order.service';
import { Order } from 'app/layout/orders/order.model';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {
  id: Number;
  editMode = false;
  items: Item[];
  orderDetail: OrderDetail;
  order:Order = new Order();
  closeResult: string;
  itemId:Number;
  
  
  createOrderForm: FormGroup = new FormGroup({
    quantity: new FormControl('')
    
  });
  constructor(private route: ActivatedRoute,
    private orderDetailService: OrderDetailService,
    private itemService: ItemService,
    private orderService: OrderService,
    private router: Router,
    private modalService: NgbModal,
  private renderer:Renderer) {
  }

  ngOnInit() {
    this.initForm();
    this.itemService.getItems()
      .subscribe(response => { this.items = response; });
      let onElement = this.renderer.selectRootElement('#search');
      onElement.focus();

  }

  onSubmit(itemId:number) {
    this.orderDetail = this.createOrderForm.value;
    this.orderDetail.itemId=itemId;
    this.orderDetail.status = "cart";
    this.orderDetail.quantityDistributor = 0;


    this.orderService.getOrdersByStatusAndRetailer("cart", parseInt(localStorage.getItem("userId")))
      .subscribe(response => {
        if (response.length > 0) {
          this.orderDetail.orderId = response[0].orderId;
          this.orderDetailService.addOrderDetail(this.orderDetail,"getOrderDetails",[])
            .subscribe(x => console.log(x));
        }
        else{
        this.order.userId = parseInt(localStorage.getItem("userId"));
        this.order.status = "cart";
        this.order.dateInitialized = new Date().toISOString();
        
        this.order.dateFinished = new Date().toISOString();
          this.orderService.addOrder(this.order,"getOrders",[])
          .subscribe(response => {
            this.orderDetail.orderId = response.orderId;
            this.orderDetailService.addOrderDetail(this.orderDetail,"getOrderDetails",[])
              .subscribe(x => console.log(x));

          });

        }

      });

      this.clearQuantity();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
  private initForm() {


  }

  open(content,itemId:number) {
    this.modalService.open(content).result.then();
      this.itemId=itemId;
      let onElement = this.renderer.selectRootElement('#quantity');
      onElement.focus();
}

clearQuantity(){
  this.createOrderForm.setValue({
    quantity: null});
    let onElement = this.renderer.selectRootElement('#search');
    onElement.focus();
}

private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
    } else {
        return  `with: ${reason}`;
    }
}


}


