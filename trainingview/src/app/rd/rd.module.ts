
import {RouterModule} from '@angular/router';
import {ToitsuSharedModule} from '../toitsu-shared/toitsu-shared.module';
import {ReactiveFormsModule} from '@angular/forms';
import {rdRouting} from './rd.routing';
import {NgModule} from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { PostComponent } from './shared/post/post.component';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {TokenInterceptor} from './token-interceptor';
import { PostTileComponent } from './shared/post-tile/post-tile.component';
import { SideBarComponent } from './shared/side-bar/side-bar.component';
import { SubredditSideBarComponent } from './shared/subreddit-side-bar/subreddit-side-bar.component';
import { VoteButtonComponent } from './shared/vote-button/vote-button.component';
import { CreateSubredditComponent } from './subreddit/create-subreddit/create-subreddit.component';
import { CreatePostComponent } from './post/create-post/create-post.component';
import { ListSubredditsComponent } from './subreddit/list-subreddits/list-subreddits.component';
import {EditorComponent} from '@tinymce/tinymce-angular';
import {ViewPostComponent} from './post/view-post/view-post.component';



@NgModule({
  declarations: [
    ViewPostComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    SignUpComponent,
    PostComponent,
    PostTileComponent,
    SideBarComponent,
    SubredditSideBarComponent,
    VoteButtonComponent,
    CreateSubredditComponent,
    CreatePostComponent,
    ListSubredditsComponent
  ],
  exports: [
    HeaderComponent,
    HomeComponent,
    ListSubredditsComponent
  ],
    imports: [
        RouterModule.forChild(rdRouting),
        ToitsuSharedModule,
        ReactiveFormsModule,
        EditorComponent

    ],
  providers: [
    {provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true}
  ]

})
export class RdModule {}
