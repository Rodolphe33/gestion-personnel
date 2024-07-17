import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";

import { Observable } from "rxjs";

import { environment } from "@gtper/env/environment.dev";
import { User } from "@gtper/shared/models/user.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  readonly #http = inject(HttpClient);
  // readonly jwtHelper = inject(JwtHelperService);

  apiUrl = `${environment.apiUrl}/user`;

  isAdmin: boolean = false;
  isBum: boolean = false;
  isRh: boolean = false;

  login(email: string, password: string): Observable<User> {
    const body = { email, password };
    return this.#http.post<User>(`${this.apiUrl}/login`, body)
      .pipe();
  }

  logout(): void {
    this.isAdmin = false;
    this.isBum = false;
    this.isRh = false;
    localStorage.clear();
  }

  register(user: User): Observable<User> {
    const body = {
      "firstName": user.firstName,
      "lastName": user.lastName,
      "email": user.email,
      "roles": user.roles,
      "password": user.password
    };
    return this.#http.post<User>(`${this.apiUrl}/register`, body);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('userId');
  }

  isAuthAdmin(): boolean {
    const userRole = localStorage.getItem('role');
    userRole ? this.isAdmin = true : this.isAdmin = false;
    return this.isAdmin;
  }

  isAuthBum(): boolean {
    const userRole = localStorage.getItem('role');
    userRole ? this.isBum = true : this.isBum = false;
    return this.isBum;
  }

  isAuthRh(): boolean {
    const userRole = localStorage.getItem('role');
    userRole ? this.isRh = true : this.isRh = false;
    return this.isRh;
  }

  // Récupération des données du localStorage
  getUserId(): string | null {
    return typeof window !== 'undefined' ? localStorage.getItem('userId') : null;
  }

  getEmail(): string | null {
    return typeof window !== 'undefined' ? localStorage.getItem('email') : null;
  }

  getFirstName(): string | null {
    return typeof window !== 'undefined' ? localStorage.getItem('firstName') : null;
  }

  getLastName(): string | null {
    return typeof window !== 'undefined' ? localStorage.getItem('lastName') : null;
  }

  getRole(): string | null {
    return typeof window !== 'undefined' ? localStorage.getItem('role') : null;
  }

  getToken(): string | null {
    return typeof window !== 'undefined' ? localStorage.getItem('token') : null;
  }

}
