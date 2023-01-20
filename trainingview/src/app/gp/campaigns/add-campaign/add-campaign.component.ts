import { Component, OnInit } from '@angular/core';
import {CampaignModel} from '../../campaign.model';
import {Router} from '@angular/router';
import {CampaignService} from '../campaign-service';

@Component({
  selector: 'app-add-campaign',
  templateUrl: './add-campaign.component.html',
  styleUrls: ['./add-campaign.component.scss']
})
export class AddCampaignComponent implements OnInit {

  campaign: CampaignModel;
  value2: any;
  date2: any;

  campaignId: number;
  campaignName: string;
  campaignDesc: string;
  campaignType: string;
  campaignCost: number;
  campaignStartDate: Date = new Date();
  campaignEndDate: Date = new Date();


  constructor(private router: Router, private campaignService: CampaignService) { }

  ngOnInit(): void {
  }

  onSubmit() {

  }

  homePage() {
    this.router.navigate(['']);
  }


  showAll() {
    this.router.navigate(['/gp/campaigns/showAll']);
  }
  onAddCampaign() {
    this.campaign = new CampaignModel(
      this.campaignId,
      this.campaignName,
      this.campaignDesc,
      this.campaignType,
      this.campaignCost,
      this.campaignStartDate,
      this.campaignEndDate);
    this.campaignService.addCampaign(this.campaign);
  }
}
