import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {ToitsuSharedModule} from '../toitsu-shared/toitsu-shared.module';
import {gpRouting} from './gp.routing';
import { CampaignsComponent } from './campaigns/campaigns.component';
import { CampaignListComponent } from './campaigns/campaign-list/campaign-list.component';
import { AddCampaignComponent } from './campaigns/add-campaign/add-campaign.component';
import { AddEditCampaignComponent } from './campaigns/add-edit-campaign/add-edit-campaign.component';
import { AuthComponent } from './auth/auth/auth.component';



@NgModule({
  declarations: [
    CampaignsComponent,
    CampaignListComponent,
    AddCampaignComponent,
    AddEditCampaignComponent,
    AuthComponent
  ],
  exports: [
    AuthComponent
  ],
  imports: [
    RouterModule.forChild(gpRouting),
    ToitsuSharedModule,

  ]

})

export class GpModule {
  
  
}
