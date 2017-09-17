import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
    NgbCarouselModule,
    NgbAlertModule
} from '@ng-bootstrap/ng-bootstrap';


import { ItemsRoutingModule } from './items-routing.module';
import { ItemsComponent } from './items.component';
import { StatModule } from '../../shared';

@NgModule({
    imports: [
        CommonModule,
        NgbCarouselModule.forRoot(),
        NgbAlertModule.forRoot(),
        ItemsRoutingModule,
        StatModule,
    ],
    declarations: [
        ItemsComponent
    ]
})
export class ItemsModule { }
