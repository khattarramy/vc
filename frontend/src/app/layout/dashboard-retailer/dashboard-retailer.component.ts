import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { Router } from '@angular/router';

@Component({
    selector: 'app-dashboard-retailer',
    templateUrl: './dashboard-retailer.component.html',
    styleUrls: ['./dashboard-retailer.component.scss'],
    animations: [routerTransition()]
})
export class DashboardRetailerComponent implements OnInit {
    public alerts: Array<any> = [];
    public sliders: Array<any> = [];

    constructor(private router:Router) {

    }

    ngOnInit() {
    }
    goToCreateOrder(){
        this.router.navigate(['/create-order']);
        
    }
    goToAllOrders(){
        this.router.navigate(['/all-orders']);
        
    }
    goToCart(){
        this.router.navigate(['/cart']);
        
    }


}
