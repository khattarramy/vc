export class OrderDetail {

  public orderDetailId: string;
  public orderId: string;
  public userId: string;
  public statusId: string;
  public itemId: string;



  constructor(orderDetailId: string, orderId: string, itemId: string, statusId: string, userId: string) {
    this.orderId = orderId;
    this.userId = userId;
    this.statusId = statusId;
    this.itemId = itemId;
    this.orderDetailId = orderDetailId;
  }
}
