import { Injectable } from '@angular/core';
import { Axios } from 'axios';
import { AxiosService } from './axios.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from './user';
import { App } from './applications/app';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  url : string
  icon : string = "../../assets/base_user_icon.jpg"
  background = "none"
  isLoggedIn : boolean  =false



  constructor(private axiosService : AxiosService, private router : Router, private route : ActivatedRoute) {
    this.url = this.axiosService.url
   }

   async getTheme() : Promise<boolean> {
    let response = await this.axiosService.request(
      "GET",
      "/options/" + window.localStorage.getItem("auth_id"),
      {}
    );
    return response.data.isDarkMode
   }

   async setTheme(mode : string) {
    let response = await this.axiosService.request(
      "GET",
      "/options/" + window.localStorage.getItem("auth_id"),
      {"isDarkMode" : mode}
    );
   }

  setBackground(background : string) : void{
    this.axiosService.request(
      "POST",
      "/options/background/" + window.localStorage.getItem("auth_id") + "/" + background,
      {})
  }

  setIcon(icon : string) : void {
    this.axiosService.request(
      "POST",
      "/options/icon/" + window.localStorage.getItem("auth_id") + "/" + icon,
      {} )
}


async changeIcon(user : User | null): Promise<string> {
  let id = window.localStorage.getItem("auth_id")
  if (user) {
    id = user.id
  }
  let response = await this.axiosService.request(
    "GET",
    "/options/" + id,
    {}
  );
  if (response.data.iconId) {
    this.icon = this.url + "/download/" + response.data.iconId;  
  }
  return this.icon;
}

async changeBackground(): Promise<string> {
  let response = await this.axiosService.request(
    "GET",
    "/options/" + window.localStorage.getItem("auth_id"),
    {}
  );
  if (response.data.backgroundId) {
    this.background = this.url + "/download/" + response.data.backgroundId;  
  }
  return this.background;
}

async checkLogin () : Promise<boolean> {
  const token = window .localStorage.getItem('auth_token')
  const id = window.localStorage.getItem('auth_id')
  if ( !token || !id) {return false}
  let response = await this.axiosService.request(
    "GET",
    "/token/" + token + "/user/" + id,
    {}).catch(
      (error) => {
          this.isLoggedIn = false
      }
    );
    this.isLoggedIn = response.data
    return this.isLoggedIn
}


async logout() : Promise<void>{
  let response = await this.axiosService.request(
    "POST",
    "/logout",
    {})
    this.axiosService.setAuthToken(null, null)
}


async getUsers() : Promise<User[]> {
  let response = await this.axiosService.request(
    "GET",
    "/users",
    {}).catch(
      (error) => {
          if (error.response.status === 401) {
              this.axiosService.setAuthToken(null, null);
          } 
      }
  );
    return response.data;
}

async getUser() : Promise<User> {
  let response = await this.axiosService.request(
    "GET",
    "/users/" + window.localStorage.getItem("auth_id"),
    {})
  return response.data
}


async deleteUser(id : string) : Promise<void> {
  let response = await this.axiosService.request(
    "DELETE",
    "/users/" + id,
    {})
    if (response.status == 200) {
      if (id == window.localStorage.getItem("auth_id")) {
        this.logout()
      }
    }
}

async getRoleById(id : string) : Promise<string> {
  let response = await this.axiosService.request(
    "GET",
    "/users/role/" + id,
    {})
    return response.data
}

async getRole() : Promise<string> {
  let role : string | null = null
  
    role = window.localStorage.getItem('auth_id')
  
  if (!role)
    return ""
  return await this.getRoleById(role);
}

async getApps() : Promise<App[]>{
  let response = await this.axiosService.request(
    "GET",
    "/apps/user/" + window.localStorage.getItem("auth_id"),
    {})
    return response.data
}

async getNotInstalledApps() : Promise<App[]> {
  let response = await this.axiosService.request(
    "GET",
    "/apps/store/user/" + window.localStorage.getItem("auth_id"),
    {})
    return response.data
}

async getImagesByCategory (category : string) : Promise<string[]> {
  let response = await this.axiosService.request(
    "GET",
    "/images/" + category  + "/" + window.localStorage.getItem("auth_id"),
    {})
    return response.data
}

resetImage (category : string) {
  this.axiosService.request(
    "DELETE",
    "/options/" + category  + "/" + window.localStorage.getItem("auth_id"),
    {})
}


async downloadApp(appId : string) {
  let response = await this.axiosService.request(
    "POST",
    "/apps/" + appId + "/user/" + window.localStorage.getItem("auth_id"),
    {})
}


}



