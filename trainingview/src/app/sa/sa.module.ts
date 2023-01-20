import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {saRoutes} from './sa.routing';
import {ToitsuSharedModule} from '../toitsu-shared/toitsu-shared.module';

import {MyUserTestComponent} from './my-user/my-user-test.component';
import { UserListComponent } from './user-list/user-list.component';
import { AddUserComponent } from './add-user/add-user.component';



@NgModule({
  declarations: [
    MyUserTestComponent,
    UserListComponent,
    AddUserComponent
  ],
  exports: [
    UserListComponent
  ],
    imports: [
        RouterModule.forChild(saRoutes),
        ToitsuSharedModule,
    ]
})
export class SaModule {
}
