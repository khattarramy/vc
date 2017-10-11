export class User{
    public userId: string;
    public type: string;
    public name: string;
    public address: string;
    public phone: string;
    public email: string;
    public username: string;
    public password: string;

    constructor(userId: string,type: string, name: string, address: string, phone: string, email: string, username: string, password: string){
        this.userId = userId;
        this.type= type;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
