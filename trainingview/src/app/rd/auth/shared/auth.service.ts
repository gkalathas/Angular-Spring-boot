import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SignupRequestPayload} from '../sign-up/signup-request.payload';
import {Observable, tap, throwError} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {authConsts} from './authConsts';
import {LocalStorageService} from 'ngx-webstorage';
import {LoginResponse} from '../login/login-response.payload';
import {LoginRequestPayload} from '../login/login-request.payload';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    username: this.getUserName()
  };

  apiBaseUrl = environment.apiRedditUrl;

  constructor(private httpClient: HttpClient,
              private localStorage: LocalStorageService) { }

  signup(signupRequestPayload: SignupRequestPayload): Observable<SignupRequestPayload> {
    return this.httpClient.post<SignupRequestPayload>(  `${this.apiBaseUrl} + ${authConsts.loginUrl}`, signupRequestPayload);
  }


  login(loginRequestPayload: LoginRequestPayload): Observable<boolean> {
    return this.httpClient.post<LoginResponse>(this.apiBaseUrl + authConsts.loginUrl, loginRequestPayload)
      .pipe(map(data => {
        this.localStorage.store('authenticationToken', data.authenticationToken);
        this.localStorage.store('username', data.username);
        this.localStorage.store('refreshToken', data.refreshToken);
        this.localStorage.store('expiresAt', data.expiresAt);
        return true;
      }));

  }

  refreshToken() {
    const refreshTokenPayload = {
      refreshToken: this.getRefreshToken(),
      username: this.getUserName()
    };

    return this.httpClient.post<LoginResponse>(this.apiBaseUrl + authConsts.refreshTokenUrl,
      refreshTokenPayload)
      .pipe(tap(response => {
        this.localStorage.store('authenticationToken', response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt);
      }));
  }

  getJwtToken() {
    return this.localStorage.retrieve('authenticationToken');
  }

  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  getUserName() {
    return this.localStorage.retrieve('username');
  }

  getExpirationTime() {
    return this.localStorage.retrieve('expiresAt');
  }



  logout() {
    this.httpClient.post(this.apiBaseUrl + authConsts.logoutUrl, this.refreshTokenPayload,
      { responseType: 'text' })
      .subscribe(data => {
        console.log(data);
      }, error => {
        throwError(error);
      });
    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('username');
    this.localStorage.clear('refreshToken');
    this.localStorage.clear('expiresAt');
  }


  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }

}
