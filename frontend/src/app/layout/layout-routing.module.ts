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
const routes: Routes = [
    {
        path: '', component: LayoutComponent,
        children: [
            { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
            { path: 'createorder', loadChildren: './create-order/create-order.module#CreateOrderModule' },
            { path: 'cart', loadChildren: './cart/cart.module#CartModule' },
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
