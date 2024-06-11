import { inject, Injectable } from '@angular/core';
import { Roles } from '@eps/shared/enums/role';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class IdentityService {
  readonly auth = inject(AuthService);

  role = this.auth.getRole();

  isAdmin(){
    if (this.role !== undefined && this.role !== null) {
      this.role === Roles.ADMIN ? true : false;
    }
  }

  isBum(){
    if (this.role!== undefined && this.role!== null) {
      this.role === Roles.BUM ? true : false;
    }
  }
}
