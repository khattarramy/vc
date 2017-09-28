export class OrderDetailHistory {

  public orderDetailHistoryId: string;
  public orderDetailId: string;
  public userId: string;
  public statusId: string;
  public dateTime: Date;



  constructor(orderDetailHistoryId: string, orderDetailId: string, dateTime: Date, statusId: string, userId: string) {
    this.orderDetailHistoryId = orderDetailHistoryId;
    this.userId = userId;
    this.statusId = statusId;
    this.dateTime = dateTime;
    this.orderDetailId = orderDetailId;
  }
}
