import { Observable } from "rxjs/Rx";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import "rxjs/Rx";
import { Order } from "./order.model";
import { Http, Response, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class OrderService {
  ordersChanged = new Subject<Order[]>();

  private orders: Order[];

  constructor(private http: Http) {}

  getOrders(): Observable<Order[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders")
      .map((response: Response) => {
        const orders: Order[] = response.json();
        return orders;
      });
  }
  getOrdersByStatusAndRetailer(status:String, retailerId: Number): Observable<Order[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders?userId=" +
      retailerId + "&status=" + status)
      .map((response: Response) => {
        const orders: Order[] = response.json();
        return orders;
      });
  }

  getOrdersByRetailer(retailerId: Number): Observable<Order[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders?userId=" +
      retailerId)
      .map((response: Response) => {
        const orders: Order[] = response.json();
        return orders;
      });
  }

  getOrder(index: Number): Observable<Order> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders/" + index)
      .map((response: Response) => {
        const order: Order = response.json();
        return order;
      });
  }

  addOrder(order: Order): Observable<Order> {
    console.log(JSON.stringify(order));
    return this.http
      .post("http://localhost:8080/valeurc/glg/orders", order)
      .map((response: Response) => {
        this.getOrders().subscribe(response => {
          this.ordersChanged.next(response);
        });
        const order: Order = response.json();
        return order;
      });
  }

  updateOrder(index: Number, newOrder: Order) {
    const x: string = "http://localhost:8080/valeurc/glg/orders/" + index;
    console.log(x);
    return this.http.put(x, newOrder).map((response: Response) => {
      this.getOrders().subscribe(response => {
        this.ordersChanged.next(response);
      });
    });
  }

  deleteOrder(index: Number) {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orders/" + index)
      .map((response: Response) => {
        this.getOrders().subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }

  deleteOrders() {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orders"); 
  }
}
