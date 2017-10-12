export class OrderDetail {

  public orderDetailId: Number;
  public orderId: Number;
  public status: string;
  public itemId: Number;
  public quantity : Number;
  public quantityDistributor : Number;
  


  constructor(orderDetailId: Number, orderId: Number, itemId: Number, 
    status: string,quantity : Number,quantityDistributor : Number) {
    this.orderId = orderId;
    this.status = status;
    this.itemId = itemId;
    this.orderDetailId = orderDetailId;
    this.quantity = quantity;
    this.quantityDistributor = quantityDistributor;
  }
}
