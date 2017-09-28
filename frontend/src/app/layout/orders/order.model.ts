export class Order {

  public orderId: string;
  public userId: string;
  public statusId: string;
  public dateInitialized: string;
  public dateFinished: string;



  constructor(orderId: string, userId: string, statusId: string, dateInitialized: string, dateFinished: string) {
    this.orderId = orderId;
    this.userId = userId;
    this.statusId = statusId;
    this.dateInitialized = dateInitialized;
    this.dateFinished = dateFinished;
  }
}
