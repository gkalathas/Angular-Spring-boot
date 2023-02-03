
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



@NgModule({
  declarations: [
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    SignUpComponent,
    PostComponent
  ],
  exports: [
    HeaderComponent,
    HomeComponent
  ],
  imports: [
    RouterModule.forChild(rdRouting),
    ToitsuSharedModule,
    ReactiveFormsModule

  ],

})
export class RdModule {}
