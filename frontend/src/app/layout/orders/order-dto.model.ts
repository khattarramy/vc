export class OrderDto {

  public orderId: Number;
  public senderName: string;
  public status: string;
  public dateInitialized: string;
  public dateFinished: string;


constructor(orderId: Number=0, senderName: string="", status: string="", 
dateInitialized: string="", dateFinished: string=""){
this.orderId=orderId;
this.senderName=senderName;
this.status=status;
this.dateInitialized=dateInitialized;
this.dateFinished=dateFinished;
}
}