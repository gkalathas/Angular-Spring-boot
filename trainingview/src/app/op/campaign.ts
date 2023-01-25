import {CampaignType} from './campaign-type';

export class Campaign {
  id: number;
  name: string;
  campaignTypeId: CampaignType;
  cost: number;
  isRunning: boolean = null;
  startDate: Date;
  endDate: Date;
  comments: string;
}
