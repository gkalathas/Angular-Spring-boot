import { Component, OnInit } from '@angular/core';
import {UserModel} from '../../user.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {


  user?: UserModel;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  users() {
    this.router.navigate(['']);
  }

  addUser() {
    this.router.navigate(['show-add-userForm']);
  }


  onSubmit() {
    // return undefined;
  }
}
