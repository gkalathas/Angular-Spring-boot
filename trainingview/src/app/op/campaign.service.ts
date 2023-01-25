import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Campaign} from './campaign';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {

  apiUrl = environment.apiBaseUrl;
  constructor(private httpClient: HttpClient) { }

  // campaignConsts: Campaign
  // getByID(id: number) {
  //   return this.httpClient.get();
  // }

  getCampaignById(id: number) {
    this.httpClient.get(`${this.apiUrl}/trn/campaigns/get?getCampaignApiParamId=id`);
  }

  saveCampaign(campaign: Campaign) {
    this.httpClient.post(`${this.apiUrl}/trn/campaigns/save`, campaign);
  }

  deleteCampaign(id: number) {
    this.httpClient.delete(`${this.apiUrl}/trn/campaigns/delete?deleteCampaignApiParamId=id`);
  }

}


