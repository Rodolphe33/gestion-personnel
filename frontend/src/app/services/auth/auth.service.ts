import { Injectable } from "@angular/core";
// import { Observable } from "rxjs";

// import { User } from "@eps/shared/models/user.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // user$: User = new Observable();

  // isAuthenticated(): boolean {
  //   if
  //   return true;
  // }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('authToken');
  }
}
