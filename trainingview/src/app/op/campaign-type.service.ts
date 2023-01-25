import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CampaignTypeService {

  private apiUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  getAll() {
    this.http.get(`${this.apiUrl}/trn/campaigntypes/getall`);
  }
}
