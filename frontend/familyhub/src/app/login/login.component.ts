import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AxiosService } from '../axios.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm! : FormGroup

  constructor (private axiosService : AxiosService, private fb : FormBuilder,  private router: Router,  private route: ActivatedRoute, private profileService : ProfileService) {}

  async ngOnInit() {
    this.loginForm = this.fb.group({
      email : ['', [Validators.required, Validators.maxLength(50)]],
      password : ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]]
    });

    if (await this.profileService.checkLogin()) {
      this.router.navigate(['../users'], { relativeTo: this.route });
    }
  }

  onLogin(input: any): void {
		this.axiosService.request(
		    "POST",
		    "/login",
		    {
		        email: input.email,
		        password: input.password
		    }).then(
		    response => {
		        this.axiosService.setAuthToken(response.data.token, response.data.id);
            this.router.navigate(['../apps'], { relativeTo: this.route });
		    }).catch(
          () => {
            alert("Bejelentkezés sikertelen")
          }
        )
	}

}
