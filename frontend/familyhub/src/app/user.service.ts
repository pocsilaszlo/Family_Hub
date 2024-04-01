import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const URL = ["http://localhost:8080"]

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http : HttpClient) { }

  public postUser(customer: any) : Observable<any> 
  {
    return this.http.post(URL + '/users', customer);
  }
}
