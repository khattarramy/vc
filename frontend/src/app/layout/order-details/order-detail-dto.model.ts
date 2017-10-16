export class OrderDetailDto {

  public orderDetailId: Number;
  public orderId: Number;
  public status: string;
  public itemId: Number;
  public itemName: string;
  public quantity : Number;
  public quantityDistributor : Number;
  


  constructor(orderDetailId: Number=0, orderId: Number=0, itemId:Number = 0 ,itemName: string="", 
    status: string="",quantity : Number=0,quantityDistributor : Number=0) {
    this.orderId = orderId;
    this.status = status;
    this.itemName = itemName;
    this.itemId = itemId
    this.orderDetailId = orderDetailId;
    this.quantity = quantity;
    this.quantityDistributor = quantityDistributor;
  }
}
