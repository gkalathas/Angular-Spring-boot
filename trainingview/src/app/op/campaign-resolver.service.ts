import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve} from '@angular/router';
import {CampaignService} from './campaign.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CampaignResolverService implements Resolve<any> {

  constructor(private campaignService: CampaignService) { }

  resolve(route: ActivatedRouteSnapshot): Observable<any> {
    return this.campaignService.getCampaignById(+route.paramMap.get('id'));
  }
}
