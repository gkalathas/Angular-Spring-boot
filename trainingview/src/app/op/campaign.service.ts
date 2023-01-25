import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {

  constructor(private httpClient: HttpClient) { }

  // campaignConsts: Campaign
  // getByID(id: number) {
  //   return this.httpClient.get();
  // }


}


