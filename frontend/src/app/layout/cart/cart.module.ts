import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
    NgbCarouselModule,
    NgbAlertModule
} from '@ng-bootstrap/ng-bootstrap';


import { CartRoutingModule } from './cart-routing.module';
import { CartComponent } from './cart.component';
import { StatModule } from '../../shared';

@NgModule({
    imports: [
        CommonModule,
        NgbCarouselModule.forRoot(),
        NgbAlertModule.forRoot(),
        CartRoutingModule,
        StatModule,
    ],
    declarations: [
        CartComponent
    ]
})
export class CartModule { }
