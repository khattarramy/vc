import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';
import { ItemsComponent } from './items/items.component';
import { ItemStartComponent } from './items/item-start/item-start.component';
import { ItemDetailComponent } from './items/item-detail/item-detail.component';
import { ItemEditComponent } from './items/item-edit/item-edit.component';
import { OrdersComponent } from './orders/orders.component';
import { OrderStartComponent } from './orders/order-start/order-start.component';
import { OrderDetailComponent } from './orders/order-detail/order-detail.component';
import { OrderEditComponent } from './orders/order-edit/order-edit.component';

import { OrderDetailsComponent } from './order-details/order-details.component';
import { OrderDetailStartComponent } from './order-details/order-detail-start/order-detail-start.component';
import { OrderDetailDetailComponent } from './order-details/order-detail-detail/order-detail-detail.component';
import { OrderDetailEditComponent } from './order-details/order-detail-edit/order-detail-edit.component';
import { OrderDetailHistoriesComponent } from "app/layout/order-detail-histories/order-detail-histories.component";
import { OrderDetailHistoryDetailComponent } from "app/layout/order-detail-histories/order-detail-history-detail/order-detail-history-detail.component";
import { OrderDetailHistoryEditComponent } from "app/layout/order-detail-histories/order-detail-history-edit/order-detail-history-edit.component";
import { OrderDetailHistoryStartComponent } from "app/layout/order-detail-histories/order-detail-history-start/order-detail-history-start.component";
import { CreateOrderComponent } from 'app/layout/create-order/create-order.component';
import { CartComponent } from 'app/layout/cart/cart.component';
import { CartDetailComponent } from 'app/layout/cart/cart-detail/cart-detail.component';
import { CartStartComponent } from 'app/layout/cart/cart-start/cart-start.component';
import { AllOrdersComponent } from 'app/layout/all-orders/all-orders.component';
import { AllOrderDetailComponent } from 'app/layout/all-orders/all-order-detail/all-order-detail.component';
import { AllOrderStartComponent } from 'app/layout/all-orders/all-order-start/all-order-start.component';
import { AllOrderEditComponent } from 'app/layout/all-orders/all-order-edit/all-order-edit.component';
import { DashboardComponent } from 'app/layout/dashboard/dashboard.component';
import { DashboardRetailerComponent } from 'app/layout/dashboard-retailer/dashboard-retailer.component';
import { DashboardManufacturerComponent } from 'app/layout/dashboard-manufacturer/dashboard-manufacturer.component';
import { DashboardDistributorComponent } from 'app/layout/dashboard-distributor/dashboard-distributor.component';
import { AllOrderDistributorEditComponent } from 'app/layout/all-orders-distributor/all-order-distributor-edit/all-order-distributor-edit.component';
import { AllOrderDistributorStartComponent } from 'app/layout/all-orders-distributor/all-order-distributor-start/all-order-distributor-start.component';
import { AllOrdersDistributorComponent } from 'app/layout/all-orders-distributor/all-orders-distributor.component';
import { AllOrderDistributorDetailComponent } from 'app/layout/all-orders-distributor/all-order-distributor-detail/all-order-distributor-detail.component';

const routes: Routes = [
    {
        path: '', component: LayoutComponent,
        children: [
            { path: 'dashboard', component:DashboardComponent },
            { path: 'dashboard-retailer', component:DashboardRetailerComponent },
            { path: 'dashboard-distributor', component:DashboardDistributorComponent },
            { path: 'dashboard-manufacturer', component:DashboardManufacturerComponent },

            {
                path: 'cart', component: CartComponent, children: [
                    { path: '', component: CartStartComponent },
                    
                    { path: ':id', component: CartDetailComponent },
                ]
            },
            {
                path: 'create-order', component: CreateOrderComponent
            },

            {
                path: 'order-details', component: OrderDetailsComponent, children: [
                    { path: '', component: OrderDetailStartComponent },
                    { path: 'new', component: OrderDetailEditComponent },
                    { path: ':id', component: OrderDetailDetailComponent },
                    { path: ':id/edit', component: OrderDetailEditComponent },
                ]
            },
            {
                path: 'order-detail-histories', component: OrderDetailHistoriesComponent, children: [
                    { path: '', component: OrderDetailHistoryStartComponent },
                    { path: 'new', component: OrderDetailHistoryEditComponent },
                    { path: ':id', component: OrderDetailHistoryDetailComponent },
                    { path: ':id/edit', component: OrderDetailHistoryEditComponent },
                ]
            },
            {
                path: 'orders', component: OrdersComponent, children: [
                    { path: '', component: OrderStartComponent },
                    { path: 'new', component: OrderEditComponent },
                    { path: ':id', component: OrderDetailComponent },
                    { path: ':id/edit', component: OrderEditComponent },
                ]
            },


            {
                path: 'all-orders', component: AllOrdersComponent, children: [
                    { path: '', component: AllOrderStartComponent },
                    { path: 'new', component: AllOrderEditComponent },
                    { path: ':id', component: AllOrderDetailComponent },
                    { path: ':id/edit', component: AllOrderEditComponent },
                ]
            },

            {
                path: 'all-orders-distributor', component: AllOrdersDistributorComponent, children: [
                    { path: '', component: AllOrderDistributorStartComponent },
                    { path: 'new', component: AllOrderDistributorEditComponent },
                    { path: ':id', component: AllOrderDistributorDetailComponent },
                    { path: ':id/edit', component: AllOrderDistributorEditComponent },
                ]
            },
            //{ path: 'items', loadChildren: './items/items.module#ItemsModule' }
            {
                path: 'items', component: ItemsComponent, children: [
                    { path: '', component: ItemStartComponent },
                    { path: 'new', component: ItemEditComponent },
                    { path: ':id', component: ItemDetailComponent },
                    { path: ':id/edit', component: ItemEditComponent },
                ]
            }

        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule { }
