export class OrderDetailHistory {

  public orderDetailHistoryId: string;
  public orderDetailId: string;
  public userId: string;
  public statusId: string;
  public dateTime: string;



  constructor(orderDetailHistoryId: string, orderDetailId: string, userId: string, statusId: string, dateTime: string) {
    this.orderDetailHistoryId = orderDetailHistoryId;
    this.userId = userId;
    this.statusId = statusId;
    this.dateTime = dateTime;
    this.orderDetailId = orderDetailId;
  }
}
