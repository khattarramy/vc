export class Item {

  public itemId: string;
  public name: string;
  public description: string;
  public modelNumber: string;
  public distributorId: string;
  public manufacturerId: string;



  constructor(itemId: string, name: string, description: string, modelNumber: string, distributorId: string,
    manufacturerId: string) {
    this.itemId = itemId;
    this.name = name;
    this.description = description;
    this.modelNumber = modelNumber;
    this.distributorId = distributorId;
    this.manufacturerId = manufacturerId;
  }
}
