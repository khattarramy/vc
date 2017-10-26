import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { Router } from '@angular/router';

@Component({
    selector: 'app-dashboard-manufacturer',
    templateUrl: './dashboard-manufacturer.component.html',
    styleUrls: ['./dashboard-manufacturer.component.scss'],
    animations: [routerTransition()]
})
export class DashboardManufacturerComponent implements OnInit {
    public alerts: Array<any> = [];
    public sliders: Array<any> = [];
    
    onClick(){
        this.router.navigate(['/all-orders-manufacturer']);
        
    }
    constructor(private router:Router) {
    }

    ngOnInit() {
    }

}
