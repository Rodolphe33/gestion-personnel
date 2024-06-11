import { Component } from '@angular/core';
import { HomeComponent } from '@eps/shared/components/home/home.component';
import { LoginComponent } from './components/auth/login/login.component';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'eps-root',
  standalone: true,
  imports: [HomeComponent, LoginComponent, TranslateModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Epsyl Prospects Service';

}
