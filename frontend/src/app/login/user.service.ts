import { Observable } from "rxjs/Rx";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import "rxjs/Rx";
import { User } from "./user.model";
import { Http, Response, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class UserService{
    usersChanged = new Subject<User[]>();
    
    // private users: User[];
    
    constructor(private http: Http) {}

    getUsers(): Observable<User[]> {
        return this.http
          .get("http://localhost:8080/valeurc/glg/users")
          .map((response: Response) => {
            const users: User[] = response.json();
            return users;
          });
    }

    getUser(index: string): Observable<User> {
        return this.http
            .get("http://localhost:8080/valeurc/glg/users/" + index)
            .map((response: Response) => {
                const user: User = response.json();
                return user;
            });
    }

    addUser(user: User) {
    console.log(JSON.stringify(user));
    return this.http
        .post("http://localhost:8080/valeurc/glg/users", user)
        .map((response: Response) => {
            this.getUsers().subscribe(response => {
                this.usersChanged.next(response);
            });
        });
    }

    updateUser(index: string, newUser: User) {
        const x: string = "http://localhost:8080/valeurc/glg/users/" + index;
        console.log(x);
        return this.http.put(x, newUser).map((response: Response) => {
            this.getUsers().subscribe(response => {
            this.usersChanged.next(response);
            });
        });
    }

    deleteOrder(index: string) {
    return this.http
        .delete("http://localhost:8080/valeurc/glg/users/" + index)
        .map((response: Response) => {
        this.getUsers().subscribe(response => {
            this.usersChanged.next(response);
        });
        });
    }

}