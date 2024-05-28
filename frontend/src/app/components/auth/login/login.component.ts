import { Component } from "@angular/core";

import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'eps-login',
  templateUrl: 'login.component.html',
  styleUrl: 'login.component.scss',
  standalone: true,
  imports: [DialogModule, ButtonModule],
})
export class LoginComponent {
  visible: boolean = false;

  showDialog() {
      this.visible = true;
  }

  closeDialog() {
      this.visible = false;
  }
}
