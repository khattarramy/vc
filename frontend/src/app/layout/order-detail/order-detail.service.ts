import { Observable } from "rxjs/Rx";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import "rxjs/Rx";
import { OrderDetail } from "./order-detail.model";
import { Http, Response, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class OrderDetailService {
  ordersChanged = new Subject<OrderDetail[]>();

  private orderDetail: OrderDetail[];

  constructor(private http: Http) {}

  getOrders(): Observable<OrderDetail[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders")
      .map((response: Response) => {
        const orderDetail: OrderDetail[] = response.json();
        return orderDetail;
      });
  }

  getOrder(index: string): Observable<OrderDetail> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders/" + index)
      .map((response: Response) => {
        const orderDetail: OrderDetail = response.json();
        return orderDetail;
      });
  }

  addOrder(order: OrderDetail) {
    console.log(JSON.stringify(order));
    return this.http
      .post("http://localhost:8080/valeurc/glg/orders", order)
      .map((response: Response) => {
        this.getOrders().subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }

  updateOrder(index: string, newOrder: OrderDetail) {
    const x: string = "http://localhost:8080/valeurc/glg/orders/" + index;
    console.log(x);
    return this.http.put(x, newOrder).map((response: Response) => {
      this.getOrders().subscribe(response => {
        this.ordersChanged.next(response);
      });
    });
  }

  deleteOrder(index: string) {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orders/" + index)
      .map((response: Response) => {
        this.getOrders().subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }
}
