import { Injectable } from '@angular/core';
import axios from 'axios';


@Injectable({
  providedIn: 'root'
})
export class AxiosService {
    id: string = ""
    url : string = 'http://localhost:8080'

  constructor() {
    axios.defaults.baseURL = this.url ;
    axios.defaults.headers.post['Content-Type'] = 'application/json';
  }

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth_token");
  }

  setAuthToken(token: string | null, id : string | null): void {
    if (token !== null && id != null) {
      window.localStorage.setItem("auth_token", token);
      window.localStorage.setItem("auth_id", id);
      //this.id = id
    } else {
      window.localStorage.removeItem("auth_token");
      //this.id = ""
      window.localStorage.removeItem("auth_id");
    }
  }


  request(method: string, url: string, data: any): Promise<any> {
      let headers: any = {};

      if (this.getAuthToken() !== null) {
          headers = {"Authorization": "Bearer " + this.getAuthToken()};
      }

      return axios({
          method: method,
          url: url,
          data: data,
          headers: headers
      });
  }


  

  
}
