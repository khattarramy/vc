export class OrderDetail {

  public orderDetailId: string;
  public orderId: string;
  public userId: Number;
  public statusId: Number;
  public itemId: Number;



  constructor(orderDetailId: string, orderId: string, itemId: Number, statusId: Number, userId: Number) {
    this.orderId = orderId;
    this.userId = userId;
    this.statusId = statusId;
    this.itemId = itemId;
    this.orderDetailId = orderDetailId;
  }
}
