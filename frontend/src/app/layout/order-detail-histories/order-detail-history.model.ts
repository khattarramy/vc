export class OrderDetailHistory {

  public orderDetailHistoryId: string;
  public orderDetailId: string;
  public userId: string;
  public status: string;
  public dateTime: string;



  constructor(orderDetailHistoryId: string, orderDetailId: string, userId: string, status: string, dateTime: string) {
    this.orderDetailHistoryId = orderDetailHistoryId;
    this.userId = userId;
    this.status = status;
    this.dateTime = dateTime;
    this.orderDetailId = orderDetailId;
  }
}
