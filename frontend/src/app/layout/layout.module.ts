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

// import { OrderDetailComponent } from './order-detail/order-detail.component';
// import { OrderDetailListComponent } from './order-detail/order-detail-list/order-detail-list.component';
// import { OrderDetailDetailComponent } from './order-detail/order-detail-detail/order-detail-detail.component';
// import { OrderDetailStartComponent } from './order-detail/order-detail-start/order-detail-start.component';
// import { OrderDetailEditComponent } from './order-detail/order-detail-edit/order-detail-edit.component';
// import { OrderDetailService } from './order-detail/order-detail.service';


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
        
        // OrderDetailComponent,
        // OrderDetailDetailComponent,
        // OrderDetailEditComponent,
        // OrderDetailListComponent,
        // OrderDetailService,
        // OrderDetailStartComponent
    ],
    providers: [ ItemService, OrderService]
})
export class LayoutModule { }
