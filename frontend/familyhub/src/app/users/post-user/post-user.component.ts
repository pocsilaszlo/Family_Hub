import { Component, OnInit } from '@angular/core';
import { UserService } from '../../user.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';


@Component({
  selector: 'app-post-user',
  standalone : true,
  imports: [ReactiveFormsModule],
  templateUrl: './post-user.component.html',
  styleUrl: './post-user.component.css'
})
export class PostUserComponent{

  postUserForm: FormGroup;

  constructor(private userService: UserService, private fb : FormBuilder) { 
    this.postUserForm = new FormGroup({});
  }

  ngOnInit() {
    this.postUserForm = this.fb.group({
      name : [null, [Validators.required]],
      password : [null, [Validators.required]],
      role : [null, [Validators.required]]
    });
  }

  postUser() {
    return this.postUserForm.value;
  }
}
