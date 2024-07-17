import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, ElementRef, inject, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LayoutService } from '@gtper/services/layout.service';
import { IfAdminDirective } from '@gtper/directives/if-admin.directive';

import { MenuItem } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { AuthService } from '@gtper/service/auth/auth.service';

@Component({
  selector: 'gtper-topbar',
  templateUrl: `topbar.component.html`,
  styleUrl: 'topbar.component.scss',
  standalone: true,
  imports: [CommonModule, RouterModule, TooltipModule, ButtonModule, IfAdminDirective],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TopbarComponent {
  items!: MenuItem[];
  @ViewChild('menubutton') menuButton!: ElementRef;
  @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;
  @ViewChild('topbarmenu') menu!: ElementRef;

  readonly layoutService = inject(LayoutService);
  readonly auth = inject(AuthService);

  firstName: string | null = '';
  user: string = '';

  ngOnInit(): void {
    this.firstName = this.auth.getFirstName();
    if(this.firstName !== null) {
      this.user = this.firstName;
    }
  }
}
