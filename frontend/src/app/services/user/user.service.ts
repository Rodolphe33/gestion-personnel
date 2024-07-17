import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "@gtper/env/environment.dev";
import { User } from "@gtper/shared/models/user.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly http = inject(HttpClient);
  readonly apiUrl = `${environment.apiUrl}/user`;

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/all`);
  }
}
