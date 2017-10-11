import { OrderDetailHistory } from './../order-detail-history.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';

import { OrderDetailHistoryService } from '../order-detail-history.service';

@Component({
  selector: 'app-order-detail-history-edit',
  templateUrl: './order-detail-history-edit.component.html',
  styleUrls: ['./order-detail-history-edit.component.css']
})
export class OrderDetailHistoryEditComponent implements OnInit {
  id: Number;
  editMode = false;

  orderDetailHistory: OrderDetailHistory;
  orderDetailHistoryForm: FormGroup = new FormGroup({
    orderDetailHistoryId: new FormControl(''),
    orderDetailId: new FormControl(''),
    userId: new FormControl(''),
    status: new FormControl(''),
    dateTime: new FormControl('')
  });
  constructor(private route: ActivatedRoute,
    private orderDetailHistoryService: OrderDetailHistoryService,
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
      console.log(this.orderDetailHistoryForm.value);
      //console.log(this.id);
        this.orderDetailHistoryService.updateOrderDetailHistory(this.id, this.orderDetailHistoryForm.value)
          .subscribe(x => console.log(x));
    } else {
     // console.log(<Order>this.orderForm.value);
      this.orderDetailHistoryService.addOrderDetailHistory(this.orderDetailHistoryForm.value)
       .subscribe(x => console.log(x));
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
  private initForm() {

    if (this.editMode) {
      this.orderDetailHistoryService.getOrderDetailHistory(this.id)
        .subscribe(response => {

      this.orderDetailHistory = response;
          this.orderDetailHistoryForm.setValue({
            orderDetailHistoryId: this.orderDetailHistory.orderDetailHistoryId,
            userId: this.orderDetailHistory.userId,
            status: this.orderDetailHistory.status,
            orderDetailId: this.orderDetailHistory.orderDetailId,
            dateTime: this.orderDetailHistory.dateTime
        });

        });


    }


  }

}


