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
import { ItemItemComponent } from './items/item-list/item-item/item-item.component';
import { DropdownDirective } from '../shared/dropdown.directive';
import { ItemStartComponent } from './items/item-start/item-start.component';
import { ItemEditComponent } from './items/item-edit/item-edit.component';
import { ItemService } from './items/item.service';

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
        ItemItemComponent,
        DropdownDirective,
        ItemStartComponent,
        ItemEditComponent
    ],
    providers: [ ItemService]
})
export class LayoutModule { }
