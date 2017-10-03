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

const routes: Routes = [
    {
        path: '', component: LayoutComponent,
        children: [
            { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
            { path: 'createorder', loadChildren: './create-order/create-order.module#CreateOrderModule' },
            { path: 'cart', loadChildren: './cart/cart.module#CartModule' },
            { path: 'order-details', component: OrderDetailsComponent, children: [
                { path: '', component: OrderDetailStartComponent },
                { path: 'new', component: OrderDetailEditComponent },
                { path: ':id', component: OrderDetailDetailComponent },
                { path: ':id/edit', component: OrderDetailEditComponent },
              ] },
              { path: 'order-detail-histories', component: OrderDetailHistoriesComponent, children: [
                { path: '', component: OrderDetailHistoryStartComponent },
                { path: 'new', component: OrderDetailHistoryEditComponent },
                { path: ':id', component: OrderDetailHistoryDetailComponent },
                { path: ':id/edit', component: OrderDetailHistoryEditComponent },
              ] },
            { path: 'orders', component: OrdersComponent, children: [
                { path: '', component: OrderStartComponent },
                { path: 'new', component: OrderEditComponent },
                { path: ':id', component: OrderDetailComponent },
                { path: ':id/edit', component: OrderEditComponent },
              ] },
            //{ path: 'items', loadChildren: './items/items.module#ItemsModule' }
            { path: 'items', component: ItemsComponent, children: [
                { path: '', component: ItemStartComponent },
                { path: 'new', component: ItemEditComponent },
                { path: ':id', component: ItemDetailComponent },
                { path: ':id/edit', component: ItemEditComponent },
              ] }

        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule { }
