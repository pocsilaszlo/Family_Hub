import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{
    
  addUserForm:any

  constructor (private userService : UserService ) {
      this.addUserForm = new FormGroup({
      name : new FormControl (),
      email : new FormControl(),
      password : new FormControl(),
      role : new FormControl()
    });
  }

  ngOnInit(): void {
      
  }

  public addUser() {
    this.userService.addUser(this.addUserForm.value).subscribe(result => console.log(result))
    
  }
}
