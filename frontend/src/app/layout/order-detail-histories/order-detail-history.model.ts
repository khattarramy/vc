export class OrderDetailHistory {

  public orderDetailHistoryId: Number;
  public orderDetailId: Number;
  public userId: Number;
  public status: string;
  public dateTime: string;



  constructor(orderDetailHistoryId: Number=0, orderDetailId: Number=0, userId: Number=0, 
    status: string="", dateTime: string="") {
    this.orderDetailHistoryId = orderDetailHistoryId;
    this.userId = userId;
    this.status = status;
    this.dateTime = dateTime;
    this.orderDetailId = orderDetailId;
  }
}
