import { Observable } from "rxjs/Rx";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import "rxjs/Rx";
import { Order } from "./order.model";
import { Http, Response, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";
import { OrderDto } from "app/layout/orders/order-dto.model";

@Injectable()
export class OrderService {
  ordersChanged = new Subject<Order[]>();

  private orders: Order[];

  constructor(private http: Http) {}

  getOrders(): Observable<OrderDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders")
      .map((response: Response) => {
        const orders: OrderDto[] = response.json();
        return orders;
      });
  }
  getOrdersByStatusAndRetailer(status:String, retailerId: Number): Observable<OrderDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders?userId=" +
      retailerId + "&status=" + status)
      .map((response: Response) => {
        const orders: OrderDto[] = response.json();
        return orders;
      });
  }
  getOrdersByStatusAndDistributor(status:String, distributorId: Number): Observable<OrderDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders?distributorId=" +
      distributorId + "&status=" + status)
      .map((response: Response) => {
        const orders: OrderDto[] = response.json();
        return orders;
      });
  }


  getOrdersByStatusAndManufacturer(status:String, manufacturerId: Number): Observable<OrderDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders?manufacturerId=" +
      manufacturerId + "&status=" + status)
      .map((response: Response) => {
        const ordersDto: OrderDto[] = response.json();
        return ordersDto;
      });
  }
  getOrdersByManufacturer(manufacturerId: Number): Observable<OrderDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders?manufacturerId=" +
      manufacturerId)
      .map((response: Response) => {
        const ordersDto: OrderDto[] = response.json();
        return ordersDto;
      });
  }

  getOrdersByDistributor(distributorId: Number): Observable<OrderDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders?distributorId=" +
      distributorId)
      .map((response: Response) => {
        const ordersDto: OrderDto[] = response.json();
        return ordersDto;
      });
  }

  getOrdersByRetailer(retailerId: Number): Observable<OrderDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orders?userId=" +
      retailerId)
      .map((response: Response) => {
        const ordersDto: OrderDto[] = response.json();
        return ordersDto;
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

  addOrder(order: Order,functionName:string,params: any[]): Observable<Order> {
    console.log(JSON.stringify(order));
    return this.http
      .post("http://localhost:8080/valeurc/glg/orders", order)
      .map((response: Response) => {
        this[functionName].apply(this,params).subscribe(response => {
          this.ordersChanged.next(response);
        });
        const order: Order = response.json();
        return order;
      });
  }

  updateOrder(index: Number, newOrder: Order,functionName:string,params: any[]) {
    const x: string = "http://localhost:8080/valeurc/glg/orders/" + index;
    console.log(x);
    return this.http.put(x, newOrder).map((response: Response) => {
      this[functionName].apply(this,params).subscribe(response => {
        this.ordersChanged.next(response);
      });
    });
  }

  deleteOrder(index: Number,functionName:string,params: any[]) {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orders/" + index)
      .map((response: Response) => {
        this[functionName].apply(this,params).subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }

  deleteOrders() {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orders"); 
  }
}
