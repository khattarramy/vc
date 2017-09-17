import { Item } from './../item.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';

import { ItemService } from '../item.service';

@Component({
  selector: 'app-item-edit',
  templateUrl: './item-edit.component.html',
  styleUrls: ['./item-edit.component.css']
})
export class ItemEditComponent implements OnInit {
  id: string;
  editMode = false;

  item: Item;
  itemForm: FormGroup = new FormGroup({
    itemId: new FormControl(''),
    name: new FormControl(''),
    description: new FormControl(''),
    modelNumber: new FormControl(''),
    distributorId: new FormControl(''),
    manufacturerId: new FormControl('')
  });
  constructor(private route: ActivatedRoute,
    private itemService: ItemService,
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
      console.log(this.itemForm.value);
      //console.log(this.id);
        this.itemService.updateItem(this.id, this.itemForm.value)
          .subscribe(x => console.log(x));
    } else {
     // console.log(<Item>this.itemForm.value);
      this.itemService.addItem(this.itemForm.value)
       .subscribe(x => console.log(x));
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
  private initForm() {

    if (this.editMode) {
      this.itemService.getItem(this.id)
        .subscribe(response => {

      this.item = response;
          this.itemForm.setValue({
            itemId: this.item.itemId, name: this.item.name,
            description: this.item.description,
            modelNumber: this.item.modelNumber,
            distributorId: this.item.distributorId,
            manufacturerId: this.item.manufacturerId});

        });


    }


  }

}


