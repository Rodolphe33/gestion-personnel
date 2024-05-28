import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "@eps/env/environment.dev";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  readonly #http = inject(HttpClient);
  apiUrl = `${environment.apiUrl}/prospect`;
}
