import { Component, EventEmitter, Output } from '@angular/core';
import { AxiosService } from '../axios.service';
import { User } from '../user';
import { App } from './app';
import { ProfileService } from '../profile.service';
import { MatDialog } from '@angular/material/dialog';
import { AppDialogComponent } from '../app-dialog/app-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-applications',
  templateUrl: './applications.component.html',
  styleUrl: './applications.component.css'
})
export class ApplicationsComponent {
  apps : App[] = [] 

  constructor(private axiosService : AxiosService, private profileService : ProfileService, private dialog: MatDialog, private router : Router, private route : ActivatedRoute) {
  }

  async ngOnInit() {
    if (await this.profileService.checkLogin() == false) {
      this.router.navigate(['../users'], { relativeTo: this.route });
    }
    this.changeBackground()
    this.apps = await this.profileService.getApps()
  }

  async changeBackground() {
    document.body.style.backgroundImage = "url('"+ await this.profileService.changeBackground() +"')";
  }

  openDialog(app : any) {
    this.dialog.open(AppDialogComponent, {
      data: app,
      width: '30rem'
    });
  }

  }

