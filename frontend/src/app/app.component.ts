import { Component } from '@angular/core';
import { HomeComponent } from '@gtper/shared/components/home/home.component';
import { LoginComponent } from './components/auth/login/login.component';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'gtper-root',
  standalone: true,
  imports: [HomeComponent, LoginComponent, TranslateModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Gestion Personnel';

}
