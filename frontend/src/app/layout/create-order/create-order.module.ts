import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
    NgbCarouselModule,
    NgbAlertModule
} from '@ng-bootstrap/ng-bootstrap';


import { CreateOrderRoutingModule } from './create-order-routing.module';
import { CreateOrderComponent } from './create-order.component';
import { StatModule } from '../../shared';

@NgModule({
    imports: [
        CommonModule,
        NgbCarouselModule.forRoot(),
        NgbAlertModule.forRoot(),
        CreateOrderRoutingModule,
        StatModule,
    ],
    declarations: [
        CreateOrderComponent
    ]
})
export class CreateOrderModule { }
