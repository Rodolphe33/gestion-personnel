import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '@gtper/services/auth/auth.service';
import { Role } from '@gtper/shared/models/role.model';
import { User } from '@gtper/shared/models/user.model';
import { ButtonModule } from 'primeng/button';

import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';

@Component({
  selector: 'gtper-login',
  templateUrl: 'login.component.html',
  styleUrl: 'login.component.scss',
  standalone: true,
  imports: [DialogModule, FormsModule, InputTextModule, PasswordModule, ButtonModule],
})
export class LoginComponent {
  readonly auth = inject(AuthService);
  readonly router = inject(Router);

  loginVisible: boolean = true;

  credatials = {
    email: '',
    password: ''
  }

  login() {
    this.auth.login(this.credatials.email, this.credatials.password).subscribe((user: User) => {
      localStorage.setItem('userId', JSON.stringify(user._id));
      localStorage.setItem('firstName', JSON.stringify(user.firstName));
      localStorage.setItem('lastName', JSON.stringify(user.lastName));
      localStorage.setItem('email', JSON.stringify(user.email));

      user.roles.forEach((role: Role) => {
        localStorage.setItem('role', JSON.stringify(role.name));
      });

      this.loginVisible = false;
      console.log(this.auth.getRole());

      this.router.navigate(['/personnal']);
    }, error => {
      console.error(`Login error: ${error}`);
    });
  }
}
