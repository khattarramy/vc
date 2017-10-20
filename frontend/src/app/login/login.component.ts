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

  addSampleData() {
    this.userService.deleteUsers().subscribe(response => {
    /* id: 1  */    this.users.push(new User("retailer", "Khoury Home", "Beirut", "03000000", "khouryhome@isae.edu.lb", "khouryhome"));
    /* id: 2  */    this.users.push(new User("retailer", "Abed Tahan", "Beirut", "03111111", "abedtahan@isae.edu.lb", "abedtahan"));
    /* id: 3  */    this.users.push(new User("retailer", "Class", "Beirut", "03222222", "class@isae.edu.lb", "class"));
    /* id: 4  */    this.users.push(new User("retailer", "CNAM", "Beirut", "03222222", "cnam@isae.edu.lb", "cnam"));
    /* id: 5  */    this.users.push(new User("distributor", "Samsung Distributor", "Beirut", "03000000", "samsungdistributor@isae.edu.lb", "samsungdistributor"));
    /* id: 6  */    this.users.push(new User("distributor", "LG Distributor", "Beirut", "03111111", "lgdistributor@isae.edu.lb", "lgdistributor"));
    /* id: 7  */    this.users.push(new User("distributor", "Sony Distributor", "Beirut", "03222222", "sonydistributor@isae.edu.lb", "sonydistributor"));
    /* id: 8  */    this.users.push(new User("distributor", "HP Distributor", "Beirut", "03000000", "hpdistributor@isae.edu.lb", "hpdistributor"));
    /* id: 9  */    this.users.push(new User("distributor", "Lenovo Distributor", "Beirut", "03111111", "lenovodistributor@isae.edu.lb", "lenovodistributor"));
    /* id: 10 */    this.users.push(new User("distributor", "Campomatic Distributor", "Beirut", "03222222", "campomaticdistributor@isae.edu.lb", "campomaticdistributor"));
    /* id: 11 */    this.users.push(new User("distributor", "Dell Distributor", "Beirut", "03222222", "delldistributor@isae.edu.lb", "delldistributor"));
    /* id: 12 */    this.users.push(new User("manufacturer", "Samsung Manufacturer", "Beirut", "03000000", "samsungmanufacturer@isae.edu.lb", "samsungmanufacturer"));
    /* id: 13 */    this.users.push(new User("manufacturer", "LG Manufacturer", "Beirut", "03111111", "lgmanufacturer@isae.edu.lb", "lgmanufacturer"));
    /* id: 14 */    this.users.push(new User("manufacturer", "Sony Manufacturer", "Beirut", "03222222", "sonymanufacturer@isae.edu.lb", "sonymanufacturer"));
    /* id: 15 */    this.users.push(new User("manufacturer", "HP Manufacturer", "Beirut", "03000000", "hpmanufacturer@isae.edu.lb", "hpmanufacturer"));
    /* id: 16 */    this.users.push(new User("manufacturer", "Lenovo Manufacturer", "Beirut", "03111111", "lenovomanufacturer@isae.edu.lb", "lenovomanufacturer"));
    /* id: 17 */    this.users.push(new User("manufacturer", "Campomatic Manufacturer", "Beirut", "03222222", "campomaticmanufacturer@isae.edu.lb", "campomaticmanufacturer"));
    /* id: 18 */    this.users.push(new User("manufacturer", "Dell Manufacturer", "Beirut", "03222222", "dellmanufacturer@isae.edu.lb", "dellmanufacturer"));
    /* id: 19 */    this.users.push(new User("admin", "admin User", "Beirut", "03222222", "admin@isae.edu.lb", "admin"));

      this.addUsers(0, this.users);

    });

    this.itemService.deleteItems().subscribe(response => {
      this.items.push(new Item("Samsung Smart TV 50 inch", "", "", 5, 12));
      this.items.push(new Item("LG Refrigerator", "", "", 6, 13));
      this.items.push(new Item("Campomatic washing machine", "", "", 10, 17));
      this.items.push(new Item("Campomatic air conditioner", "", "", 10, 17));
      this.items.push(new Item("Sony home theatre", "", "", 7, 14));
      this.items.push(new Item("HP Desk pro 3600", "", "", 8, 15));
      this.items.push(new Item("Lenovo thinkpad", "", "", 9, 16));
      this.items.push(new Item("Samsung galaxy S8", "", "", 5, 12));
      this.items.push(new Item("LG TV 55 inch", "", "", 6, 13));
      this.items.push(new Item("HP LaserJet Printer 5001", "", "", 8, 15));
      this.items.push(new Item("Dell XPS", "", "", 11, 18));
      this.addItems(0, this.items);
    });


    this.OrderService.deleteOrders().subscribe();
    this.orderDetailService.deleteOrderDetails().subscribe();
    this.orderDetailHistoryService.deleteOrderDetailHistories().subscribe();

  }

  addUsers(i: number, users: User[]) {
    if (i == users.length) {
      alert("Users added successfully")
      return;
    } else {
      this.userService.addUser(users[i]).subscribe(response => {
        this.addUsers(i + 1, users);
      })
    }
  }

  addItems(i: number, items: Item[]) {
    if (i == items.length) {
      alert("Items added successfully")
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
