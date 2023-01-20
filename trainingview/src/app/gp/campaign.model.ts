

export class CampaignModel {

  id: number;

  name: string;
  campaignTypeDescription: string;
  campaignTypeKind: string;

  cost: number;

  startDate: string;

  endDate: string;


  constructor(id: number, name: string, campaignTypeDescription: string, campaignTypeKind: string, cost: number, startDate: string, endDate: string) {
    this.id = id;
    this.name = name;
    this.campaignTypeDescription = campaignTypeDescription;
    this.campaignTypeKind = campaignTypeKind;
    this.cost = cost;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
