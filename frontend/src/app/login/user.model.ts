export class User{
    public userId: string;
    public typeId: string;
    public name: string;
    public address: string;
    public phone: string;
    public email: string;
    public username: string;
    public password: string;

    constructor(userId: string,typeId: string, name: string, address: string, phone: string, email: string, username: string, password: string){
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
