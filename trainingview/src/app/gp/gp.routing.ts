
import {Routes} from '@angular/router';
import {CampaignListComponent} from './campaigns/campaign-list/campaign-list.component';
import {AddCampaignComponent} from './campaigns/add-campaign/add-campaign.component';

export const gpRouting: Routes = [{
  path: 'campaigns', children: [
    {path: 'showAll', component: CampaignListComponent, data: {title: 'gp.campaigns', breadcrumbs: [
          {label: 'gp.campaigns', routerLink: ['/gp/campaigns/showAll']}], permissions: []}
    },
    {path: 'addCampaign', component: AddCampaignComponent, data: {title: 'gp.addCampaign', breadcrumbs: [
          {label: 'gp.addCampaign', routerLink: ['/gp/campaigns/addCampaign']}], permissions: []}
    }
  ]
}];

