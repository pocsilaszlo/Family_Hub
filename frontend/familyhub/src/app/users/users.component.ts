import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AxiosService } from '../axios.service';
import { User } from '../user';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent implements OnInit{
  users : User[] = []

  constructor(private axiosService : AxiosService, private profileService : ProfileService) {}

  async ngOnInit() {
    await this.getUsers()
    let isLoggedIn = await this.profileService.checkLogin();
    if (this.users.length == 0 && await !isLoggedIn) {
      this.axiosService.setAuthToken(null, null)
    }
    if (isLoggedIn)
      await this.changeBackground()
}

  async changeBackground() {
    document.body.style.backgroundImage = "url('"+ await this.profileService.changeBackground() +"')";
  }

  getParentCount() : number {
    return this.users.filter((user : User) => user.role == "parent").length
  }

  getChildCount() : number {
    return this.users.filter((user : User) => user.role == "child").length
  }

  async getUsers() : Promise<void> {
      this.users = await this.profileService.getUsers()
  }

  simulate() : void {
    this.axiosService.request(
      "POST",
      "/simulation",
      {}).then(
      () => {
        window.location.reload()
      });
  }
  
}
