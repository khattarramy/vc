export class Item {

  public itemId: Number;
  public name: string;
  public description: string;
  public modelNumber: string;
  public distributorId: Number;
  public manufacturerId: Number;



  constructor(name: string="", description: string="", 
  modelNumber: string="", distributorId: Number=0,
    manufacturerId: Number=0) {
    this.name = name;
    this.description = description;
    this.modelNumber = modelNumber;
    this.distributorId = distributorId;
    this.manufacturerId = manufacturerId;
  }
}
