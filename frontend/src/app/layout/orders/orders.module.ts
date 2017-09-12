import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
    NgbCarouselModule,
    NgbAlertModule
} from '@ng-bootstrap/ng-bootstrap';


import { OrdersRoutingModule } from './orders-routing.module';
import { OrdersComponent } from './orders.component';
import { StatModule } from '../../shared';

@NgModule({
    imports: [
        CommonModule,
        NgbCarouselModule.forRoot(),
        NgbAlertModule.forRoot(),
        OrdersRoutingModule,
        StatModule,
    ],
    declarations: [
        OrdersComponent
    ]
})
export class OrdersModule { }
