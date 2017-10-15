import { LoginService } from './login.service';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { ItemService } from 'app/layout/items/item.service';
import { OrderDetailHistoryService } from 'app/layout/order-detail-histories/order-detail-history.service';
import { OrderDetailService } from 'app/layout/order-details/order-detail.service';
import { OrderService } from 'app/layout/orders/order.service';

@NgModule({
    imports: [
        CommonModule,
        LoginRoutingModule,
        FormsModule,
        ReactiveFormsModule,
    ],
    declarations: [LoginComponent]
    ,
    providers: [ LoginService,OrderService,OrderDetailService,OrderDetailHistoryService,ItemService]
})
export class LoginModule {
}
