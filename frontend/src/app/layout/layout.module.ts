import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { LayoutRoutingModule } from './layout-routing.module';
import { LayoutComponent } from './layout.component';
import { HeaderComponent, SidebarComponent } from '../shared';
import { ItemsComponent } from './items/items.component';
import { ItemListComponent } from './items/item-list/item-list.component';
import { ItemDetailComponent } from './items/item-detail/item-detail.component';
import { DropdownDirective } from '../shared/dropdown.directive';
import { ItemStartComponent } from './items/item-start/item-start.component';
import { ItemEditComponent } from './items/item-edit/item-edit.component';
import { ItemService } from './items/item.service';

import { OrdersComponent } from './orders/orders.component';
import { OrderListComponent } from './orders/order-list/order-list.component';
import { OrderDetailComponent } from './orders/order-detail/order-detail.component';
import { OrderStartComponent } from './orders/order-start/order-start.component';
import { OrderEditComponent } from './orders/order-edit/order-edit.component';
import { OrderService } from './orders/order.service';

import { OrderDetailsComponent } from './order-details/order-details.component';
import { OrderDetailListComponent } from './order-details/order-detail-list/order-detail-list.component';
import { OrderDetailDetailComponent } from './order-details/order-detail-detail/order-detail-detail.component';
import { OrderDetailStartComponent } from './order-details/order-detail-start/order-detail-start.component';
import { OrderDetailEditComponent } from './order-details/order-detail-edit/order-detail-edit.component';
import { OrderDetailService } from './order-details/order-detail.service';

import { OrderDetailHistoriesComponent } from './order-detail-histories/order-detail-histories.component';
import { OrderDetailHistoryListComponent } from './order-detail-histories/order-detail-history-list/order-detail-history-list.component';
import { OrderDetailHistoryDetailComponent } from './order-detail-histories/order-detail-history-detail/order-detail-history-detail.component';
import { OrderDetailHistoryStartComponent } from './order-detail-histories/order-detail-history-start/order-detail-history-start.component';
import { OrderDetailHistoryEditComponent } from './order-detail-histories/order-detail-history-edit/order-detail-history-edit.component';
import { OrderDetailHistoryService } from './order-detail-histories/order-detail-history.service';
import { CreateOrderComponent } from 'app/layout/create-order/create-order.component';
import { CartComponent } from 'app/layout/cart/cart.component';
import { CartDetailComponent } from 'app/layout/cart/cart-detail/cart-detail.component';
import { CartListComponent } from 'app/layout/cart/cart-list/cart-list.component';
import { CartStartComponent } from 'app/layout/cart/cart-start/cart-start.component';
import { AllOrderStartComponent } from 'app/layout/all-orders/all-order-start/all-order-start.component';
import { AllOrderListComponent } from 'app/layout/all-orders/all-order-list/all-order-list.component';
import { AllOrderEditComponent } from 'app/layout/all-orders/all-order-edit/all-order-edit.component';
import { AllOrderDetailComponent } from 'app/layout/all-orders/all-order-detail/all-order-detail.component';
import { AllOrdersComponent } from 'app/layout/all-orders/all-orders.component';
import { DashboardRetailerComponent } from 'app/layout/dashboard-retailer/dashboard-retailer.component';
import { DashboardComponent } from 'app/layout/dashboard/dashboard.component';
import { DashboardDistributorComponent } from 'app/layout/dashboard-distributor/dashboard-distributor.component';
import { DashboardManufacturerComponent } from 'app/layout/dashboard-manufacturer/dashboard-manufacturer.component';


@NgModule({
    imports: [
        CommonModule,
        NgbDropdownModule.forRoot(),
        LayoutRoutingModule,
        TranslateModule,

        FormsModule,
        ReactiveFormsModule,
        HttpModule

    ],
    declarations: [
        LayoutComponent,
        HeaderComponent,
        SidebarComponent,
        ItemsComponent,
        ItemListComponent,
        ItemDetailComponent,
        OrdersComponent,
        OrderListComponent,
        OrderDetailComponent,

        DropdownDirective,
        ItemStartComponent,
        ItemEditComponent,
        OrderStartComponent,
        OrderEditComponent,

        OrderDetailsComponent,
        OrderDetailDetailComponent,
        OrderDetailEditComponent,
        OrderDetailListComponent,
        OrderDetailStartComponent,

        CartComponent,
        CartDetailComponent,
        CartListComponent,
        CartStartComponent,


        OrderDetailHistoriesComponent,
        OrderDetailHistoryDetailComponent,
        OrderDetailHistoryEditComponent,
        OrderDetailHistoryListComponent,
        OrderDetailHistoryStartComponent,


        AllOrdersComponent,
        AllOrderDetailComponent,
        AllOrderEditComponent,
        AllOrderListComponent,
        AllOrderStartComponent,



        CreateOrderComponent,

        CartComponent,

        DashboardComponent,

        DashboardRetailerComponent,

        DashboardDistributorComponent,

        DashboardManufacturerComponent


    ],
    providers: [ ItemService, OrderService , OrderDetailService, OrderDetailHistoryService]
})
export class LayoutModule { }
