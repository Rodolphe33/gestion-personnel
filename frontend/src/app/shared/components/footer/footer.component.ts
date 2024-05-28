import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'eps-footer',
  standalone: true,
  imports: [
    CommonModule,
  ],
  template: `
  <div class="layout-footer">
    <span class="font-medium ml-2">Epsyl-Alcen &copy;</span>
  </div>
`,
  styleUrl: './footer.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FooterComponent { }
