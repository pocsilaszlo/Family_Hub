import { Component, OnInit } from '@angular/core';
import { AxiosService } from '../axios.service';
import { User } from '../user';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { FileService } from '../file.service';
import { ActivatedRoute, Router } from '@angular/router';
import { App } from '../applications/app';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  user : User | undefined;
  selectedIcon: File | undefined;
  selectedBackground : File | undefined;
  iconUploadProgress: number | undefined;
  backgrounUploadProgress: number | undefined
  icons : string[] = []
  backgrounds: string[] = []
  apps : App[] = []
  icon : string = "../../assets/base_user_icon.jpg"
  url :string = this.axiosService.url + "/download/"


  constructor(private axiosService : AxiosService, private fileService: FileService, private profileService : ProfileService, private router : Router, private route : ActivatedRoute) {
  }

  async ngOnInit() {
    if (await this.profileService.checkLogin() == false) {
      this.router.navigate(['../users'], { relativeTo: this.route });
    }

    await this.changeIcon()
    await this.changeBackground()
    await this.getData();
    await this.getAppStore();
    await this.getImages("icon");
    await this.getImages("background")
  }

  async resetIcon() {
    await this.profileService.resetImage('icon')
    this.icon = "../../assets/base_user_icon.jpg"
    window.location.reload()
  }

  async resetBackground() {
    await this.profileService.resetImage('background')
    window.location.reload()
  }

  async changeIcon() {    
    this.icon = await this.profileService.changeIcon(null)
  }

  async changeBackground() {
    document.body.style.backgroundImage = "url('"+ await this.profileService.changeBackground() +"')";
  }

  setBackground(background : string) : void {
    this.profileService.setBackground(background)
    window.location.reload()
  }

  setIcon(icon : string) : void {
    this.profileService.setIcon(icon);
    window.location.reload()
  }

  onFileSelected(event: any, type : string): void {
    const fileList: FileList = event.target.files;
    if (fileList && fileList.length > 0) {
      if (type == "icon") 
        this.selectedIcon = fileList[0];
      
      if (type == "background")
        this.selectedBackground = fileList[0];
    }
  }

  uploadFile(selectedFile : File | undefined, type : string): void {
    if (selectedFile) {
      this.fileService.uploadFile(selectedFile, type)
        .subscribe(progress => {
          if (type = "icon")
          this.iconUploadProgress = progress;
          if (type = "background")
          this.backgrounUploadProgress = progress;
          if (progress === 100) {
            console.log("File upload completed")
            selectedFile = undefined;
            window.location.reload()
          }
        });
    }
  }

  async getData() {
    this.user = await this.profileService.getUser()
  }

  async getAppStore() {
    this.apps = await this.profileService.getNotInstalledApps()
  }

  async downloadApp(appId : string) {
    await this.profileService.downloadApp(appId)
    window.location.reload()
  }
  async getImages(category : string) {
    if (category == 'icon') {
      this.icons = await this.profileService.getImagesByCategory(category)
    }

    if (category == 'background') {
      this.backgrounds = await this.profileService.getImagesByCategory(category)
    }
  }

  
}
