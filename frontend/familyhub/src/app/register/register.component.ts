import { Component, EventEmitter, Output, input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AxiosService } from '../axios.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm!: FormGroup
  parentCount : number = 0

  constructor (private axiosService : AxiosService, private fb : FormBuilder ,  private router: Router,  private route: ActivatedRoute, private profileService : ProfileService) {
  }

  async ngOnInit() {
    this.registerForm = this.fb.group({
      name : ['', [Validators.required, Validators.maxLength(40)]],
      email : ['', [Validators.required, Validators.email, Validators.maxLength(50)]],
      password : ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      role : ['', Validators.required]
    });

    if (await this.profileService.checkLogin) {
      if(await this.profileService.getRole() == 'child') {
        this.router.navigate(['../users'], { relativeTo: this.route });
    }}

    
    this.parentCount = (await this.profileService.getUsers()).filter(user => user.role == 'parent').length
  }


	onRegister(input: any): void {
    console.log("hallo")
		this.axiosService.request(
		    "POST",
		    "/register",
		    {
		        name: input.name,
		        email: input.email,
		        password: input.password,
            role: input.role
		    }).then(
		      () => {
          this.router.navigate(['../users'], { relativeTo: this.route }); 
		    });
	}
}
