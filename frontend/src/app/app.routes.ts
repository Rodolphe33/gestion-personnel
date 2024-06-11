import { Routes } from '@angular/router';

// import { adminGuard } from './components/auth/admin.guard';
import { authGuard } from './components/auth/auth.guard';

import { LoginComponent } from '@eps/components/auth/login/login.component';
import { ListProspectsComponent } from './components/list-prospects/list-prospects.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UserComponent } from './components/user/user.component';
import { NotFoundComponent } from './components/notFound/not-found.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'prospect',
    loadComponent: () =>
      import('./components/list-prospects/list-prospects.component').then(() => ListProspectsComponent)
  },
  {
    path: 'admin',
    canActivate: [authGuard],
    children: [
      {
        path: 'user',
        loadComponent: () =>
          import('./components/user/user.component').then(() => UserComponent)
      },
      {
        path: 'dashboard',
        loadComponent: () =>
          import('./components/dashboard/dashboard.component').then(() => DashboardComponent)
      }
    ]
  },
  {
    path: '**',
    loadComponent: () =>
      import('./components/notFound/not-found.component').then(
        () => NotFoundComponent
      ),
  },
];
