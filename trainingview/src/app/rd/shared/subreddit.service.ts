import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SubredditModel} from './subreddit-model';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {sharedConsts} from './sharedConsts';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {

  apiBaseUrl = environment.apiRedditUrl;
  constructor(private httpClient: HttpClient) { }


  getAllSubreddits(): Observable<Array<SubredditModel>> {
    return this.httpClient.get<Array<SubredditModel>>(this.apiBaseUrl + sharedConsts.getAllSubredditsUrl);
  }

  createSubreddit(subredditModel: SubredditModel): Observable<SubredditModel> {
    return this.httpClient.post<SubredditModel>(this.apiBaseUrl + sharedConsts.getAllSubredditsUrl,
      subredditModel);
  }

}
