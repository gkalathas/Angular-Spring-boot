import {Injectable} from '@angular/core';
import {CampaignModel} from '../campaign.model';


@Injectable({
  providedIn: 'root'
})
export class CampaignService {


campaigns: CampaignModel[] = [
  new CampaignModel(1, 'test1', 'testCampaign', 'marketing', 200,
    '11/12/1996', '12/12/1992'),
  new CampaignModel(2, 'test2', 'testCampaign', 'marketing', 200,
   '11/12/1996', '12/12/1992'),
  new CampaignModel(3, 'test3', 'testCampaign', 'marketing', 200,
    '11/12/1996', '12/12/1992'),
  new CampaignModel(4, 'test4', 'testCampaign', 'marketing', 200,
    '11/12/1996', '12/12/1992'),
  new CampaignModel(5, 'test5', 'testCampaign', 'marketing', 200,
    '11/12/1996', '12/12/1992'),
  new CampaignModel(6, 'test6', 'testCampaign', 'marketing', 200,
    '11/12/1996', '12/12/1992'),
  new CampaignModel(7, 'test7', 'testCampaign', 'marketing', 200,
   '11/12/1996', '12/12/1992'),
  new CampaignModel(8, 'test8', 'testCampaign', 'marketing', 200,
   '11/12/1996', '12/12/1992'),
  new CampaignModel(9, 'test9', 'testCampaign', 'marketing', 200,
    '11/12/1996', '12/12/1992'),
];



  constructor() {

  }

  addCampaign(campaign: CampaignModel) {
    this.campaigns.push(campaign);
  }
}
