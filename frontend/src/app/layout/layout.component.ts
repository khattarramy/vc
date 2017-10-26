import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

    constructor(public router: Router) { }

    ngOnInit() {
        if (this.router.url === '/' && localStorage.getItem('type') === 'admin') {
            this.router.navigate(['/dashboard']);
        }        
        else if (this.router.url === '/' && localStorage.getItem('type') === 'retailer') {
            this.router.navigate(['/dashboard-retailer']);
        }
        else if (this.router.url === '/' && localStorage.getItem('type') === 'distributor') {
            this.router.navigate(['/dashboard-distributor']);
        }
        else if (this.router.url === '/' && localStorage.getItem('type') === 'manufacturer') {
            this.router.navigate(['/dashboard-manufacturer']);
        }
    }

}
