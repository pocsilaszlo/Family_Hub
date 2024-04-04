import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ApplicationsComponent } from './applications/applications.component';
import { SettingsComponent } from './settings/settings.component';

const routes: Routes = [
  {path : 'users', component : UserComponent},
  { path: '',   redirectTo: '/users', pathMatch: 'full' },
  {path : 'login', component : LoginComponent},
  {path : 'register', component : RegisterComponent},
  {path : 'apps', component : ApplicationsComponent},
  {path : 'settings', component : SettingsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
