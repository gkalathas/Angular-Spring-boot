
import {HomeComponent} from './home/home.component';
import {SignUpComponent} from './auth/sign-up/sign-up.component';
import {LoginComponent} from './auth/login/login.component';
import {CreateSubredditComponent} from './subreddit/create-subreddit/create-subreddit.component';
import {CreatePostComponent} from './post/create-post/create-post.component';

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
      },
      {path: 'create-subreddit', component: CreateSubredditComponent, data: {title: 'rd.reddit.createSubreddit', breadcrumbs: [
            {label: 'rd.createSubreddit', routerLink: ['/rd/reddit/create-subreddit']}], permissions: []}
      },
      {path: 'create-post', component: CreatePostComponent, data: {title: 'rd.reddit.createPost', breadcrumbs: [
            {label: 'rd.createPost', routerLink: ['/rd/reddit/createPost']}], permissions: []}
      },
      {path: 'list-subreddits', component: CreatePostComponent, data: {title: 'rd.reddit.subredditList', breadcrumbs: [
            {label: 'rd.subredditList', routerLink: ['/rd/reddit/list-subreddits']}], permissions: []}
      }
    ]}
  
];
