import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Campaign} from './campaign';
import {ToitsuSharedService} from '../toitsu-shared/toitsu-shared.service';
import {campaignConsts} from './campaign.consts';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {

  apiUrl = environment.apiBaseUrl;
  constructor(private httpClient: HttpClient,
              private toitsouSharedService: ToitsuSharedService) { }

  getCampaignById(id: number) {
    return this.httpClient.get(this.apiUrl + campaignConsts.getById,
      {params: this.toitsouSharedService.initHttpParams({id})});
  }

  saveCampaign(campaign: Campaign): Observable<Campaign> {
    return this.httpClient.post<Campaign>(this.apiUrl + campaignConsts.saveUrl, campaign);
  }

  deleteCampaign(id: number) {
    return this.httpClient.delete(this.apiUrl + campaignConsts.deleteUrl, {
      params: this.toitsouSharedService.initHttpParams({id})});
  }

}


