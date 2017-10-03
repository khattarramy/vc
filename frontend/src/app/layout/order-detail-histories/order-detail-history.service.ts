import { Observable } from "rxjs/Rx";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import "rxjs/Rx";
import { OrderDetailHistory } from "./order-detail-history.model";
import { Http, Response, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class OrderDetailHistoryService {
  ordersChanged = new Subject<OrderDetailHistory[]>();

  private orderDetailHistory: OrderDetailHistory[];

  constructor(private http: Http) {}

  getOrdersDetailHistory(): Observable<OrderDetailHistory[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orderdetailhistory")
      .map((response: Response) => {
        const orderDetailHistory: OrderDetailHistory[] = response.json();
        return orderDetailHistory;
      });
  }

  getOrderDetailHistory(index: string): Observable<OrderDetailHistory> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orderdetailhistory/" + index)
      .map((response: Response) => {
        const orderDetailHistory: OrderDetailHistory = response.json();
        return orderDetailHistory;
      });
  }

  addOrderDetailHistory(orderDetailHistory: OrderDetailHistory) {
    console.log(JSON.stringify(orderDetailHistory));
    return this.http
      .post(
        "http://localhost:8080/valeurc/glg/orderdetailhistory",
        orderDetailHistory
      )
      .map((response: Response) => {
        this.getOrdersDetailHistory().subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }

  updateOrderDetailHistory(index: string, newOrderDetailHistory: OrderDetailHistory) {
    const x: string =
      "http://localhost:8080/valeurc/glg/orderdetailhistory/" + index;
    console.log(x);
    return this.http.put(x, newOrderDetailHistory).map((response: Response) => {
      this.getOrdersDetailHistory().subscribe(response => {
        this.ordersChanged.next(response);
      });
    });
  }

  deleteOrderDetailHistory(index: string) {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orderdetailhistory/" + index)
      .map((response: Response) => {
        this.getOrdersDetailHistory().subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }
}
