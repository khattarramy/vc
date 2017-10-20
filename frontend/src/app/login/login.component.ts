import { User } from "./user.model";
import { LoginService } from "./login.service";

import { Login } from "./login.model";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { routerTransition } from "../router.animations";
import { FormGroup, FormControl, FormArray, Validators } from "@angular/forms";
import { UserService } from "app/login/user.service";
import { OrderService } from "app/layout/orders/order.service";
import { OrderDetailService } from "app/layout/order-details/order-detail.service";
import { OrderDetailHistoryService } from "app/layout/order-detail-histories/order-detail-history.service";
import { ItemService } from "app/layout/items/item.service";
import { Item } from "app/layout/items/item.model";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {
  user: User;
  users: User[] = new Array<User>();
  items: Item[] = new Array<Item>();
  i: Number = 0;
  loginForm: FormGroup = new FormGroup({
    email: new FormControl(""),
    password: new FormControl("")
  });

  constructor(
    public router: Router,
    private route: ActivatedRoute,
    private loginService: LoginService,
    private userService: UserService,
    private OrderService: OrderService,
    private orderDetailService: OrderDetailService,
    private orderDetailHistoryService: OrderDetailHistoryService,
    private itemService: ItemService,
  ) { }

  ngOnInit() { }
  clearOldData() {
    this.userService.deleteUsers().subscribe();
    this.OrderService.deleteOrders().subscribe();
    this.orderDetailService.deleteOrderDetails().subscribe();
    this.orderDetailHistoryService.deleteOrderDetailHistories().subscribe();
    this.itemService.deleteItems().subscribe();
  }
  addSampleData() {

    this.users.push(new User("retailer", "Retailer1 User", "Beirut", "03000000", "retailer1@isae.edu.lb", "retailer1"));
    this.users.push(new User("retailer", "Retailer2 User", "Beirut", "03111111", "retailer2@isae.edu.lb", "retailer2"));
    this.users.push(new User("retailer", "Retailer3 User", "Beirut", "03222222", "retailer3@isae.edu.lb", "retailer3"));

    this.users.push(new User("distributor", "Distributor1 User", "Beirut", "03000000", "distributor1@isae.edu.lb", "distributor1"));
    this.users.push(new User("distributor", "Distributor2 User", "Beirut", "03111111", "distributor2@isae.edu.lb", "distributor2"));
    this.users.push(new User("distributor", "Distributor3 User", "Beirut", "03222222", "distributor3@isae.edu.lb", "distributor3"));

    this.users.push(new User("manufacturer", "Manufacturer1 User", "Beirut", "03000000", "manufacturer1@isae.edu.lb", "manufacturer1"));
    this.users.push(new User("manufacturer", "Manufacturer2 User", "Beirut", "03111111", "manufacturer2@isae.edu.lb", "manufacturer2"));
    this.users.push(new User("manufacturer", "Manufacturer3 User", "Beirut", "03222222", "manufacturer3@isae.edu.lb", "manufacturer3"));

    this.users.push(new User("admin", "admin User", "Beirut", "03222222", "admin@isae.edu.lb", "admin"));

    this.addUsers(0, this.users);


    this.items.push(new Item("TV","Samsung LED TV","9qpfjpqwf",4,5));
    this.items.push(new Item("Phone","Samsung S7","afsa3f3f",4,7));
    this.items.push(new Item("Laptop","Lenovo Laptop","33fasfasf",6,8));
    
    this.addItems(0,this.items);
  }

  addUsers(i: number, users: User[]) {
    if (i == users.length) {
      return;
    } else {
      this.userService.addUser(users[i]).subscribe(response => {
        this.addUsers(i + 1, users);
      })
    }
  }

  addItems(i: number, items: Item[]) {
    if (i == items.length) {
      return;
    } else {
      this.itemService.addItem(items[i]).subscribe(response => {
        this.addItems(i + 1, items);
      })
    }

  }

  onSubmit() {
    this.loginService.loginUser(this.loginForm.value).subscribe(response => {

      this.user = response;
      if (this.user.userId == 0) {
        alert("Wrong credentials!");

      } else {
        localStorage.setItem('email', this.user.email);
        localStorage.setItem('password', this.user.password);
        localStorage.setItem('userId', String(this.user.userId));
        localStorage.setItem('type', String(this.user.type));

        if (this.user.type == "retailer") { this.router.navigate(['/dashboard-retailer']); }
        else if (this.user.type == "distributor") { this.router.navigate(['/dashboard-distributor']); }
        else if (this.user.type == "manufacturer") { this.router.navigate(['/dashboard-manufacturer']); }
        else if (this.user.type == "admin") { this.router.navigate(['/dashboard']); }


      }

    });
  }
}
