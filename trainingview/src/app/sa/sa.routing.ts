import {Routes} from '@angular/router';

import {MyUserTestComponent} from './my-user/my-user-test.component';
import {ArticleComponent} from './article/article.component';

export const saRoutes: Routes = [
    {
      path: 'article', children: [
          {path: 'test', component: MyUserTestComponent, data: {title: 'sa.myUser', breadcrumbs: [
                {label: 'sa.myUser', routerLink: ['/sa/article/test']}], permissions: []}
          },
          {path: 'list', component: ArticleComponent, data: {title: 'sa.articlelist', breadcrumbs: [
                {label: 'sa.articlelist', routerLink: ['/sa/article/list']}], permissions: []}
          },
      ]},

];

