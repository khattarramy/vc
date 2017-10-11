export class Item {

  public itemId: Number;
  public name: string;
  public description: string;
  public modelNumber: string;
  public distributorId: Number;
  public manufacturerId: Number;



  constructor(itemId: Number, name: string, description: string, modelNumber: string, distributorId: Number,
    manufacturerId: Number) {
    this.itemId = itemId;
    this.name = name;
    this.description = description;
    this.modelNumber = modelNumber;
    this.distributorId = distributorId;
    this.manufacturerId = manufacturerId;
  }
}
