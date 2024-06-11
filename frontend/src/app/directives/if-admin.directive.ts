import { NgIf } from '@angular/common';
import { Directive, inject, OnInit } from '@angular/core';
import { AuthService } from '@eps/services/auth/auth.service';
import { IdentityService } from '@eps/services/auth/identity.service';

@Directive({
  selector: '[epsIfAdmin]',
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
