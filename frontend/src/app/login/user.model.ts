export class User{
    public userId: string;
    public typeId: Number;
    public name: string;
    public address: string;
    public phone: Number;
    public email: string;
    public username: string;
    public password: string;

    constructor(userId: string,typeId: Number, name: string, address: string, phone: Number, email: string, username: string, password: string){
        this.userId = userId;
        this.typeId = typeId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}