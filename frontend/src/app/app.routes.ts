import { Routes } from '@angular/router';
// import { inject } from '@angular/core';

// import { AuthentificationService } from '@eps/services/auth/authentification.service';

import { HomeComponent } from '@eps/shared/components/home/home.component';
import { NotFoundComponent } from './components/notFound/not-found.component';
import { UserComponent } from './components/user/user.component';
import { ListProspectsComponent } from './components/prospects/list-prospects/list-prospects.component';
// import { LoginComponent } from '@eps/components/auth/login/login.component';
// import { AuthGuard } from './components/auth/auth.guard';

export const routes: Routes = [
  // {
  //   path: 'login',
  //   component: LoginComponent
  // },
  {
    path: '',
    component: HomeComponent,
    // canActivate: [AuthGuard],
    children: [
      {
        path: 'prospect',
        component: ListProspectsComponent,
      },
      {
        path: 'user',
        component: UserComponent,
      },
      // {
      //   path: '',
      //   canMatch: [() => inject(AuthentificationService).isAuthenticated()],
      // },

      // {
      //   path: 'register',
      //   component: UserComponent,
      // },
    ],
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];
