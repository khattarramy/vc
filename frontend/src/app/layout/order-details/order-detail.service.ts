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

  getOrderDetails(): Observable<OrderDetail[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orderdetails")
      .map((response: Response) => {
        const orderDetail: OrderDetail[] = response.json();
        return orderDetail;
      });
  }

  getOrderDetailsByStatusAndRetailer(status:String, retailerId: Number): Observable<OrderDetail[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orderdetails?userId=" +
      retailerId + "&status=" + status)
      .map((response: Response) => {
        const orderDetails: OrderDetail[] = response.json();
        return orderDetails;
      });
  }

  getOrderDetail(index: Number): Observable<OrderDetail> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orderdetails/" + index)
      .map((response: Response) => {
        const orderDetail: OrderDetail = response.json();
        return orderDetail;
      });
  }

  addOrderDetail(order: OrderDetail) {
    console.log(JSON.stringify(order));
    return this.http
      .post("http://localhost:8080/valeurc/glg/orderdetails", order)
      .map((response: Response) => {
        this.getOrderDetails().subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }

  updateOrderDetail(index: Number, newOrder: OrderDetail) {
    const x: string = "http://localhost:8080/valeurc/glg/orderdetails/" + index;
    console.log(x);
    return this.http.put(x, newOrder).map((response: Response) => {
      this.getOrderDetails().subscribe(response => {
        this.ordersChanged.next(response);
      });
    });
  }

  deleteOrderDetail(index: Number) {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orderdetails/" + index)
      .map((response: Response) => {
        this.getOrderDetails().subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }
  deleteOrderDetails() {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orderdetails");
  }
}
