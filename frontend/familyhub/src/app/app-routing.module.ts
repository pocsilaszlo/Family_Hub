import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './users/users.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ApplicationsComponent } from './applications/applications.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  {path : 'users', component : UsersComponent},
  {path: '',   redirectTo: '/users', pathMatch: 'full' },
  {path : 'login', component : LoginComponent},
  {path : 'register', component : RegisterComponent},
  {path : 'apps', component : ApplicationsComponent},
  {path : 'profile', component : ProfileComponent},
  {path: '**',  redirectTo: '/users', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
