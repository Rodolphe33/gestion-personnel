import { NgIf } from '@angular/common';
import { Directive, inject, OnInit } from '@angular/core';
import { AuthService } from '@gtper/services/auth/auth.service';
import { IdentityService } from '@gtper/services/auth/identity.service';

@Directive({
  selector: '[gtper-IfAdmin]',
  standalone: true,
  hostDirectives: [{
    directive: NgIf
  }]
})
export class IfAdminDirective implements OnInit {
  private identityService = inject(IdentityService);
  private authService = inject(AuthService);
  private ngIfDirective = inject(NgIf);

  ngOnInit() {
    this.ngIfDirective.ngIf = this.authService.isAuthAdmin() && this.identityService.isAdmin();
  }
}
