import { PostUserComponent } from "./users/post-user/post-user.component";
import { AppComponent } from "./app.component";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { UserService } from "./user.service";
import { NgModule } from "@angular/core";



@NgModule({
    declarations: [
        AppComponent,
        PostUserComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule
    ],
    providers: [
        UserService
    ],
    exports : [
        ReactiveFormsModule
    ],
    bootstrap: [AppComponent]
}) export class AppModule { }
/*
import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app-routing.module';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes)]
};
*/