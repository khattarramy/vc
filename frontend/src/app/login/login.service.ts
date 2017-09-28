import { Observable } from "rxjs/Rx";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import "rxjs/Rx";
import { User } from "./user.model";
import { Login } from "./login.model";
import { Http, Response, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class LoginService {
  usersChanged = new Subject<User[]>();

  // private users: User[];

  constructor(private http: Http) {}

  loginUser(login: Login): Observable<User> {
    console.log(JSON.stringify(login));
    return this.http
      .post("http://localhost:8080/valeurc/glg/login", login)
      .map((response: Response) => {
        const user: User = response.json();
        return user;
      });
  }
}
