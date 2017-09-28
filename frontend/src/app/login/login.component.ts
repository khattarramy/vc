import { User } from "./user.model";
import { LoginService } from "./login.service";

import { Login } from "./login.model";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { routerTransition } from "../router.animations";
import { FormGroup, FormControl, FormArray, Validators } from "@angular/forms";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {
  user: User;
  loginForm: FormGroup = new FormGroup({
    email: new FormControl(""),
    password: new FormControl("")
  });

  constructor(
    public router: Router,
    private route: ActivatedRoute,
    private loginService: LoginService
  ) {}

  ngOnInit() {}

  onSubmit() {
    //alert("Submitted!");

    // console.log(<Item>this.itemForm.value);
    this.loginService.loginUser(this.loginForm.value).subscribe(response => {
      this.user = response;
      //alert(this.login.email);
      localStorage.setItem('email', this.user.email);
      localStorage.setItem('password', this.user.password);
      localStorage.setItem('userId', this.user.userId);
      localStorage.setItem('typeId', String(this.user.typeId));
      this.router.navigate(['/dashboard']);

    });
  }
}
