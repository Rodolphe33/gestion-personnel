import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, ElementRef, inject, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LayoutService } from '@eps/service/layout.service';
import { MenuItem } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';

@Component({
  selector: 'eps-topbar',
  templateUrl: `topbar.component.html`,
  styleUrl: 'topbar.component.scss',
  standalone: true,
  imports: [CommonModule, RouterModule, TooltipModule, ButtonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TopbarComponent {
  items!: MenuItem[];
  @ViewChild('menubutton') menuButton!: ElementRef;
  @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;
  @ViewChild('topbarmenu') menu!: ElementRef;

  layoutService = inject(LayoutService);
}
