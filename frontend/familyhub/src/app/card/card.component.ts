import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from '../user';
import { AxiosService } from '../axios.service';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent implements OnInit{
  
  @Input() user : User  | undefined
  @Input() parentCount : number = 0
  @Input() childCount : number = 0
  isLoggedIn = false
  authId : string | null= ""
  authRole : string  = ""
  icon : string = '../../assets/base_user_icon.jpg'

  constructor(private axiosService : AxiosService, private profileService : ProfileService){
  }


  async ngOnInit(): Promise<void> {
    
    await this.checkLogin()

    if (this.user != undefined) 
     await this.changeIcon(this.user)

    this.authRole = await this.profileService.getRole()
    
  }

  async changeIcon(user : User | null) {    
    this.icon = await this.profileService.changeIcon(user)
  }

   async checkLogin() : Promise<void> {
      this.isLoggedIn = await this.profileService.checkLogin();
   }

   async deleteUser(id : string | undefined) : Promise<void>{
    if (id) {
      await this.profileService.deleteUser(id);
      window.location.reload()
    }
   }

}
