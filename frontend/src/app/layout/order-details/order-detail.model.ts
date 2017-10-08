export class OrderDetail {

  public orderDetailId: string;
  public orderId: string;
  public retailerId: string;
  public statusId: string;
  public itemId: string;
  public quantity : Number;
  public quantityDistributor : Number;
  


  constructor(orderDetailId: string, orderId: string, itemId: string, 
    statusId: string, retailerId: string,quantity : Number,quantityDistributor : Number) {
    this.orderId = orderId;
    this.retailerId = retailerId;
    this.statusId = statusId;
    this.itemId = itemId;
    this.orderDetailId = orderDetailId;
    this.quantity = quantity;
    this.quantityDistributor = quantityDistributor;
  }
}
