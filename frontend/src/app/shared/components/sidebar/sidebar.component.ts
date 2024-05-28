import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, ElementRef, inject } from '@angular/core';
import { LayoutService } from '@eps/service/layout.service';

@Component({
  selector: 'eps-sidebar',
  standalone: true,
  imports: [
    CommonModule,
  ],
  template: ``,
  styleUrl: './sidebar.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SidebarComponent {
  layoutService = inject(LayoutService);
  el = inject(ElementRef);
}
