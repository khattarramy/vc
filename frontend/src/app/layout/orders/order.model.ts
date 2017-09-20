export class Order {

  public orderId: string;
  public userId: Number;
  public statusId: Number;
  public dateInitialized: string;
  public dateFinished: string;



  constructor(orderId: string, userId: Number, statusId: Number, dateInitialized: string, dateFinished: string) {
    this.orderId = orderId;
    this.userId = userId;
    this.statusId = statusId;
    this.dateInitialized = dateInitialized;
    this.dateFinished = dateFinished;
  }
}
