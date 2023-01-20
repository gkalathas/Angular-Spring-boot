import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  templateUrl: './my-user-test.component.html'
})
export class MyUserTestComponent implements OnInit {
  
  testUser = {
    lastName: 'Test',
    firstName: 'User'
  };
  
  constructor(private http: HttpClient, private router: Router) {}
  
  ngOnInit() {
  }

  // users() {
  //   this.router.navigate(['/sa/myuser/test']);
  // }
  //
  // addUser() {
  //   this.router.navigate(['show-add-userForm']);
  // }
  //
  // user() {
  //   this.router.navigate(['/sa/myuser/test']);
  // }
}
