import { Injectable } from '@angular/core';
import { Roles } from '@eps/shared/enums/role';

@Injectable({
  providedIn: 'root'
})
export class IdentityService {

  role = localStorage.getItem('roles');

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
