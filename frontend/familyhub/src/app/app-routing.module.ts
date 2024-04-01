import { RouterModule, Routes, provideRouter } from '@angular/router';
import { PostUserComponent } from './users/post-user/post-user.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
    { path: '**', component: PostUserComponent },
    {path : 'users', component : PostUserComponent}
];


export const appRouterProviders = [
    provideRouter(routes)
  ];
  

@NgModule ({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ],
    declarations: []
}) export class AppRoutingModule {}