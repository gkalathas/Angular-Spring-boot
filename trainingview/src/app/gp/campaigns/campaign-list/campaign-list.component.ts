import { Component, OnInit } from '@angular/core';
import {CampaignService} from '../campaign-service';
import {CampaignModel} from '../../campaign.model';
import {TranslateService} from '@ngx-translate/core';
import {Router} from '@angular/router';


@Component({
  selector: 'app-campaign-list',
  templateUrl: './campaign-list.component.html',
  styleUrls: ['./campaign-list.component.scss']
})
export class CampaignListComponent implements OnInit {

  text: string = 'Add Campaigns';
  campaigns: CampaignModel[];
  campaign1: any;
  constructor(private campaignService: CampaignService, private translateService: TranslateService,
              private router: Router) { }


  ngOnInit(): void {
    this.campaigns = this.campaignService.campaigns;
    // this.translateService.onLangChange.subscribe(event => {
    //   console.log(event.lang);
    //   this.text = this.translateService.instant('my-user-el.json');
    //
    // });

  }

  home() {

  }

  addCampaign() {
    this.router.navigate(['/gp/campaigns/addCampaign']);
  }

  selectedEmployee() {

  }

  deleteCampaign(id: number) {

    // delete this.campaigns[id];
    // const template = this.campaigns.indexOf(CampaignModel, id);
    // this.campaigns.slice(id);

    // const temp = (id) => {
    //   const reset = this.campaigns.filter(obj => obj.id !== id);
    //   return res;
    // }

  }
}
