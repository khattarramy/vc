import { Pipe, PipeTransform } from '@angular/core';
import { Order } from 'app/layout/orders/order.model';

@Pipe({
    name: 'itemfilter',
    pure: false
})
export class ItemFilterPipe implements PipeTransform {
    transform(items: any[], filter: string): any {
        if (!items || !filter) {
            return items;
        }
        // filter items array, items which match and return true will be kept, false will be filtered out
        return items.filter(item => item.name.indexOf(filter) !== -1);
    }
}