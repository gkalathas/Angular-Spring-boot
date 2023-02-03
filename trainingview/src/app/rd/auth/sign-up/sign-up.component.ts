import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {SignupRequestPayload} from './signup-request.payload';
import {Router} from '@angular/router';
import {AuthService} from '../shared/auth.service';
import {ToitsuToasterService} from '../../../toitsu-shared/toitsu-toaster/toitsu-toaster.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signupRequestPayload: SignupRequestPayload;
  signupForm: FormGroup;
  constructor(private router: Router,
              private authService: AuthService,
              private toitsuToasterService: ToitsuToasterService) {
    this.signupRequestPayload = {
      username: '',
      email: '',
      password: ''
    };
  }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    });


  }

  signup() {
    this.signupRequestPayload.username = this.signupForm.get('username').value;
    this.signupRequestPayload.email = this.signupForm.get('email').value;
    this.signupRequestPayload.password = this.signupForm.get('password').value;


    this.authService.signup(this.signupRequestPayload).subscribe(() => {
      console.log('Signup Successful');
      this.router.navigate(['/rd/reddit/login'], { queryParams: { registered: 'true' } });
    }, () => {
      console.log('Signup Failed');
      this.toitsuToasterService.showErrorStay('Registration Failed! Please try again');
    });
  }
}
