import { Component } from "@angular/core";
import { RouterModule } from "@angular/router";

@Component({
  selector: 'eps-not-found',
  templateUrl: 'not-found.component.html',
  styleUrl: 'not-found.component.scss',
  standalone: true,
  imports: [RouterModule],
})
export class NotFoundComponent {
  title = 'Page not found';
}
