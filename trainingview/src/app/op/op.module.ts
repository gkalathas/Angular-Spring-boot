import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ToitsuSharedModule} from '../toitsu-shared/toitsu-shared.module';
import {ReactiveFormsModule} from '@angular/forms';
import {OpRouting} from './op.routing';
import { CampaignListComponent } from './campaign/campaign-list/campaign-list.component';
import { CampaignViewComponent } from './campaign/campaign-view/campaign-view.component';


@NgModule({
  declarations: [
    CampaignListComponent,
    CampaignViewComponent
  ],
  exports: [],
  imports: [
    RouterModule.forChild(OpRouting),
    ToitsuSharedModule,
    ReactiveFormsModule

  ]

})
export class OpModule { }
