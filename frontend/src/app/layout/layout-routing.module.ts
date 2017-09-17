import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
    {
        path: '', component: LayoutComponent,
        children: [
            { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
            { path: 'createorder', loadChildren: './create-order/create-order.module#CreateOrderModule' },
            { path: 'cart', loadChildren: './cart/cart.module#CartModule' },
            { path: 'orders', loadChildren: './orders/orders.module#OrdersModule' }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule { }
