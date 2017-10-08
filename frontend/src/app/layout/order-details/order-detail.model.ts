export class OrderDetail {

  public orderDetailId: string;
  public orderId: string;
  public retailerId: string;
  public status: string;
  public itemId: string;
  public quantity : Number;
  public quantityDistributor : Number;
  


  constructor(orderDetailId: string, orderId: string, itemId: string, 
    status: string, retailerId: string,quantity : Number,quantityDistributor : Number) {
    this.orderId = orderId;
    this.retailerId = retailerId;
    this.status = status;
    this.itemId = itemId;
    this.orderDetailId = orderDetailId;
    this.quantity = quantity;
    this.quantityDistributor = quantityDistributor;
  }
}
