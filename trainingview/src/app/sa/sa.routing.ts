import {Routes} from '@angular/router';

import {UserListComponent} from './user-list/user-list.component';
import {AddUserComponent} from './add-user/add-user.component';

export const saRoutes: Routes = [
  {path: '', children: [
    {
      path: 'myuser', children: [
          {path: 'test', component: UserListComponent, data: {title: 'sa.myUser', breadcrumbs: [
                {label: 'sa.myUser', routerLink: ['/sa/myuser/test']}], permissions: []}
          }]
      },
      {
        path: 'userlist', children: [
          {path: 'show-list', component: UserListComponent, data: {title: 'sa.userlist', breadcrumbs: [
                {label: 'sa.userlist', routerLink: ['show-list']}], permissions: []}
          }]
      }]
  },


  {path: 'show-list', component: UserListComponent},
  {path: 'show-add-userForm', component: AddUserComponent}
];
