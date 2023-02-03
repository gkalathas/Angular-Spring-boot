import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../shared/auth.service';
import {LoginRequestPayload} from './login-request.payload';
import {ActivatedRoute, Router} from '@angular/router';
import {ToitsuToasterService} from '../../../toitsu-shared/toitsu-toaster/toitsu-toaster.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  isError: boolean;
  loginRequestPayload: LoginRequestPayload;
  registerSuccessMessage: string;

  constructor(private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private toitsuToasterService: ToitsuToasterService,
              private router: Router) {
    this.loginRequestPayload = {
      username: '',
      password: ''
    };
  }

  ngOnInit(): void {

    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });


    this.activatedRoute.queryParams
      .subscribe(params => {
        if (params.registered !== undefined && params.registered === 'true') {
          this.toitsuToasterService.showSuccessStay('Signup Successful');
          this.registerSuccessMessage = 'Please Check your inbox for activation email '
            + 'activate your account before you Login!';
        }
      });

  }

  login() {

    this.loginRequestPayload.username = this.loginForm.get('username').value;
    this.loginRequestPayload.password = this.loginForm.get('password').value;

    this.authService.login(this.loginRequestPayload).subscribe(data => {
      if (data) {
        this.isError = false;
        this.router.navigateByUrl('/');
        this.toitsuToasterService.showSuccessStay('Login Successful');
      } else {
        this.isError = true;
      }

    });
  }
}
