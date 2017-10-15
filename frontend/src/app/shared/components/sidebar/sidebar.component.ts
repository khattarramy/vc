import { Component, OnInit } from '@angular/core';
import { User } from 'app/login/user.model';

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
    user: User = new User();
    isActive = false;
    showMenu = '';
    eventCalled() {
        this.isActive = !this.isActive;
    }

    ngOnInit() {

        this.user.email = localStorage.getItem('email');
        this.user.password = localStorage.getItem('password');
        this.user.userId = parseInt(localStorage.getItem('userId'));
        this.user.type = localStorage.getItem('type');
    }

    addExpandClass(element: any) {
        if (element === this.showMenu) {
            this.showMenu = '0';
        } else {
            this.showMenu = element;
        }
    }
}
