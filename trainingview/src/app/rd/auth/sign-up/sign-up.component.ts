import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
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
              private toitsuToasterService: ToitsuToasterService,
              private formBuilder: FormBuilder) {
    this.signupRequestPayload = {
      username: '',
      email: '',
      password: ''
    };
  }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    });


  }


  signup() {
    // this.signupRequestPayload.username = this.signupForm.get('username').value;
    // this.signupRequestPayload.email = this.signupForm.get('email').value;
    // this.signupRequestPayload.password = this.signupForm.get('password').value;


    this.authService.signup(this.signupForm.value).subscribe(response => {
      console.log(response);
      this.signupForm.reset();
      this.toitsuToasterService.showSuccessStay('Registration Successful!');
      this.router.navigate(['/rd/reddit/login'], {queryParams: {registered: 'true'}})
        .then(r => console.log(r));
     }, () => {
      console.log();
      this.toitsuToasterService.showErrorStay('Registration Failed! Please try again');
    });
  }
}
