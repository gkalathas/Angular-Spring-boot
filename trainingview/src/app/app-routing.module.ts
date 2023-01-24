import {Routes} from '@angular/router';
import {IndexComponent} from './toitsu-layout/index/index.component';
import {Toitsu401Component} from './toitsu-shared/toitsu-401/toitsu-401.component';
import {Toitsu403Component} from './toitsu-shared/toitsu-403/toitsu-403.component';

export const appRoutes: Routes = [
  {path: '', component: IndexComponent},
  {path: '401', component: Toitsu401Component, data: {title: 'global.error.401.title'}},
  {path: '403', component: Toitsu403Component, data: {title: 'global.error.403.title'}},
  {path: 'sa', loadChildren: () => import('./sa/sa.module').then(m => m.SaModule)},
  {path: 'gp', loadChildren: () => import('./gp/gp.module').then(m => m.GpModule)},
  {path: 'cd', loadChildren: () => import('./cd/cd.module').then(m => m.CdModule)},
  {path: 'op', loadChildren: () => import('./op/op.module').then(m => m.OpModule)},
  {path: '**', redirectTo: ''},
];

export class AppRoutingModule {
}
