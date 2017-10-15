export class Order {

  public orderId: Number;
  public userId: Number;
  public status: string;
  public dateInitialized: string;
  public dateFinished: string;


constructor(orderId: Number=0, userId: Number=0, status: string="", 
dateInitialized: string="", dateFinished: string=""){
this.orderId=orderId;
this.userId=userId;
this.status=status;
this.dateInitialized=dateInitialized;
this.dateFinished=dateFinished;
}
}