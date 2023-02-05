import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../auth/shared/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean;
  username: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();
  }

  goToUserProfile() {
    this.router.navigateByUrl('/user-profile/' + this.username).then(r => console.log(r));
  }

  logout() {

    this.authService.logout();
    this.router.navigateByUrl('').then(() => {
      window.location.reload();
    });
  }
}
