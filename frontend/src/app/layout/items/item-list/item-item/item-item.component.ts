import { Component, OnInit, Input } from '@angular/core';

import { Item } from '../../item.model';

@Component({
  selector: 'app-item-item',
  templateUrl: './item-item.component.html',
  styleUrls: ['./item-item.component.css']
})
export class ItemItemComponent implements OnInit {
  @Input() item: Item;
  @Input() index: number;

  ngOnInit() {
  }
}
