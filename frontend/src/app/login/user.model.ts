export class User{
    public userId: Number;
    public type: string;
    public name: string;
    public address: string;
    public phone: string;
    public email: string;
    public password: string;

    constructor(type: string="", name: string="", 
    address: string="", phone: string="", email: string="", password: string=""){
      
        this.type= type;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}
