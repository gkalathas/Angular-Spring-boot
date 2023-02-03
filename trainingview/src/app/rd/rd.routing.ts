
import {ExitConfirmationGuard} from '../toitsu-shared/exit-confirmation.guard';
import {HomeComponent} from './home/home.component';
import {SignUpComponent} from './auth/sign-up/sign-up.component';
import {LoginComponent} from './auth/login/login.component';

export const rdRouting = [
  {path: 'reddit', children: [
      {path: 'home', component: HomeComponent, data: {title: 'rd.reddit.home', breadcrumbs: [
            {label: 'rd.home', routerLink: ['/rd/reddit/home']}], permissions: []}
      },
      {path: 'sign-up', component: SignUpComponent, data: {title: 'rd.reddit.sign-up', breadcrumbs: [
            {label: 'rd.sign-up', routerLink: ['/rd/reddit/sign-up']}], permissions: []}
      },
      {path: 'login', component: LoginComponent, data: {title: 'rd.reddit.login', breadcrumbs: [
            {label: 'rd.login', routerLink: ['/rd/reddit/login']}], permissions: []}
      }
    ]}
  
];
