import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
    selector: 'app-create-order',
    templateUrl: './create-order.component.html',
    styleUrls: ['./create-order.component.scss'],
    animations: [routerTransition()]
})
export class CreateOrderComponent implements OnInit {


    ngOnInit() {
    }


}
