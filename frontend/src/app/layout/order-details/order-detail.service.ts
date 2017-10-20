import { Observable } from "rxjs/Rx";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import "rxjs/Rx";
import { OrderDetail } from "./order-detail.model";
import { Http, Response, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";
import { OrderDetailDto } from "app/layout/order-details/order-detail-dto.model";

@Injectable()
export class OrderDetailService {
  ordersChanged = new Subject<OrderDetail[]>();

  private orderDetail: OrderDetail[];

  constructor(private http: Http) {}

  getOrderDetails(): Observable<OrderDetailDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orderdetails")
      .map((response: Response) => {
        const orderDetailDto: OrderDetailDto[] = response.json();
        return orderDetailDto;
      });
  }

  getOrderDetailsByStatusAndRetailer(status:String, retailerId: Number): Observable<OrderDetailDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orderdetails?retailerId=" +
      retailerId + "&status=" + status)
      .map((response: Response) => {
        const orderDetailDto: OrderDetailDto[] = response.json();
        return orderDetailDto;
      });
  }


  getOrderDetailsByOrderAndDistributor(orderId:Number, distributorId: Number): Observable<OrderDetailDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orderdetails?orderId=" +
      orderId + "&distributorId=" + distributorId)
      .map((response: Response) => {
        const orderDetailDto: OrderDetailDto[] = response.json();
        return orderDetailDto;
      });
  }

  getOrderDetailsByOrderAndManufacturer(orderId:Number, manufacturerId: Number): Observable<OrderDetailDto[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/orderdetails?orderId=" +
      orderId + "&manufacturerId=" + manufacturerId)
      .map((response: Response) => {
        const orderDetailDto: OrderDetailDto[] = response.json();
        return orderDetailDto;
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

  addOrderDetail(order: OrderDetail,functionName:string,params: any[]) {
    console.log(JSON.stringify(order));
    return this.http
      .post("http://localhost:8080/valeurc/glg/orderdetails", order)
      .map((response: Response) => {
        this[functionName].apply(this,params).subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }

  updateOrderDetail(index: Number, newOrder: OrderDetail,functionName:string,params: any[]) {
    const x: string = "http://localhost:8080/valeurc/glg/orderdetails/" + index;
    console.log(x);
    return this.http.put(x, newOrder).map((response: Response) => {
      this[functionName].apply(this,params).subscribe(response => {
        this.ordersChanged.next(response);
      });
    });
  }

  deleteOrderDetail(index: Number,functionName:string,params: any[]) {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orderdetails/" + index)
      .map((response: Response) => {
        this[functionName].apply(this,params).subscribe(response => {
          this.ordersChanged.next(response);
        });
      });
  }
  deleteOrderDetails() {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/orderdetails");
  }
}
