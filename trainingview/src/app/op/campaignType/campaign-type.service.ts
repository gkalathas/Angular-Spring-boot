import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {campaignTypeConsts} from './campaignType.consts';
import {ToitsuSharedService} from '../../toitsu-shared/toitsu-shared.service';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CampaignTypeService {

  private apiUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient,
              private toitsuSharedService: ToitsuSharedService) { }

  getAll() {
    return this.http.get<{}[]>(this.apiUrl + campaignTypeConsts.getAll,
      {
        params: this.toitsuSharedService.initHttpParams({})
      }
      ).pipe(
        map( response => {
          return response.map(responseItem => {
            return {
              value: responseItem['id'],
              label: responseItem['description']
            };
          });
        })
    );
  }
}
