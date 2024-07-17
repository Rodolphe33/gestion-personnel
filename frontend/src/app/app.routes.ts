import { Routes } from '@angular/router';

// import { adminGuard } from './components/auth/admin.guard';
import { authGuard } from './components/auth/auth.guard';

import { LoginComponent } from '@gtper/components/auth/login/login.component';
import { ListPersonnalsComponent } from './components/list-personnals/list-personnals.component';
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
    path: 'personnal',
    loadComponent: () =>
      import('./components/list-personnals/list-personnals.component').then(() => ListPersonnalsComponent)
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
