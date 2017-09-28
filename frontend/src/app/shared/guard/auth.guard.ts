import { Injectable } from "@angular/core";
import { CanActivate } from "@angular/router";
import { Router } from "@angular/router";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate() {
    if (
      localStorage.getItem('email') &&
      localStorage.getItem('password') &&
      localStorage.getItem('userId') &&
      localStorage.getItem('typeId')
    ) {
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }
}
