import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{

  isLoggedIn : boolean = false
  authRole : string = ""

  constructor(private router : Router, private route: ActivatedRoute,private profileService : ProfileService) {}

  async ngOnInit() {
   await this.checkLogin()
   
   if (this.isLoggedIn) {
    
    
    this.authRole = await this.profileService.getRole()
   }
  }

  async checkLogin() : Promise<void> {
     this.isLoggedIn = await this.profileService.checkLogin();
  }

  async logout() : Promise<void> {
    await this.profileService.logout()
    await this.router.navigate(['../users'], { relativeTo: this.route })
    window.location.reload()
    document.body.style.backgroundImage = 'none'
  }
 
}
