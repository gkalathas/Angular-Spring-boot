import { Component, OnInit } from '@angular/core';
import {UserModel} from '../../user.model';
import {UserService} from '../../user-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  users: UserModel[];


  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.users = this.userService.users;
  }


  user() {
    this.router.navigate(['']);
  }

  addUser() {
    this.router.navigate(['show-add-userForm']);
  }




}
