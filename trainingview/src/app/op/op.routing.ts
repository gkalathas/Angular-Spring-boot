

import {CampaignListComponent} from './campaign/campaign-list/campaign-list.component';
import {CampaignViewComponent} from './campaign/campaign-view/campaign-view.component';
import {CampaignResolverService} from './campaign-resolver.service';
import {ExitConfirmationGuard} from '../toitsu-shared/exit-confirmation.guard';


export const OpRouting = [
  {
    path: 'campaign', children: [
      {path: 'list', component: CampaignListComponent, data: {title: 'op.campaign.list', breadcrumbs: [
            {label: 'op.list', routerLink: ['/op/campaign/list']}], permissions: []}
      },
      {path: 'view', component: CampaignViewComponent, canDeactivate: [ExitConfirmationGuard], data: {title: 'op.campaign.view', breadcrumbs: [
            {label: 'op.view', routerLink: ['/op/campaign/view']}], permissions: []}
      },
      {path: 'view/:id', component: CampaignViewComponent, canDeactivate: [ExitConfirmationGuard], resolve: {record: CampaignResolverService},
        data: {title: 'op.campaign.edit', breadcrumbs: [
            {label: 'op.view.edit', routerLink: ['/op/campaign/view']}], permissions: []}
      }
    ]
  }

];
