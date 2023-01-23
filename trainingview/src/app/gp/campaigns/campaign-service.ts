import {Injectable} from '@angular/core';
import {CampaignModel} from '../campaign.model';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {ToitsuSharedService} from '../../toitsu-shared/toitsu-shared.service';


@Injectable({
  providedIn: 'root'
})
export class CampaignService {
  



campaigns: CampaignModel[] = [
  new CampaignModel(1, 'test1', 'testCampaign', 'marketing', 200,
    new Date('11/12/1996'), new Date('12/12/1992')),
  new CampaignModel(2, 'test2', 'testCampaign', 'marketing', 200,
   new Date('11/12/1996'), new Date('12/12/1992')),
  new CampaignModel(3, 'test3', 'testCampaign', 'marketing', 200,
    new Date('11/12/1996'), new Date('12/12/1992')),
  new CampaignModel(4, 'test4', 'testCampaign', 'marketing', 200,
    new Date('11/12/1996'), new Date('12/12/1992')),
  new CampaignModel(5, 'test5', 'testCampaign', 'marketing', 200,
    new Date('11/12/1996'), new Date('12/12/1992')),
  new CampaignModel(6, 'test6', 'testCampaign', 'marketing', 200,
    new Date('11/12/1996'), new Date('12/12/1992')),
  new CampaignModel(7, 'test7', 'testCampaign', 'marketing', 200,
   new Date('11/12/1996'), new Date('12/12/1992')),
  new CampaignModel(8, 'test8', 'testCampaign', 'marketing', 200,
   new Date('11/12/1996'), new Date('12/12/1992')),
  new CampaignModel(9, 'test9', 'testCampaign', 'marketing', 200,
    new Date('11/12/1996'), new Date('12/12/1992')),
];

 args = {
  name: 'Campaign Name',
   campaignTypeId: 1000,
   
  };


  private apiServerUrl = environment.newUrl;

  constructor(private http: HttpClient, private toitsouService: ToitsuSharedService) {
  }


  createCampaign(campaign: CampaignModel): Observable<CampaignModel> {
    return this.http.post<CampaignModel>( `${this.apiServerUrl}/save`, campaign);
  }

  loadData($event: any): Observable<CampaignModel[]> {
    return this.http.get<CampaignModel[]>
    (`${this.apiServerUrl}/trn/campaigns/getAll?page=${$event.first / $event.rows}&size=${$event.rows}`);
  }
  loadDataById(id: number): Observable<CampaignModel> {
    return this.http.get<CampaignModel>(`${this.apiServerUrl}/get?id=${id}`);
  }

  loadData1($event: any, campaign: CampaignModel): Observable<CampaignModel> {
    return this.http.post<CampaignModel>
    (`${this.apiServerUrl}/trn/campaigns/getAll?page=${$event.first / $event.rows}&size=${$event.rows}`, campaign);
  }

  deleteSingleCampaign(id: number) {
    return this.http.delete(`${this.apiServerUrl}/delete?id`);
  }


  addCampaign(campaign: CampaignModel) {
    this.campaigns.push(campaign);
  }
}
