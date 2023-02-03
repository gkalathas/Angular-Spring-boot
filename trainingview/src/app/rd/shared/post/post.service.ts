import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostModel } from './post-model';
import {environment} from '../../../../environments/environment';
import {sharedConsts} from '../sharedConsts';
import {CreatePostPayload} from '../../post/create-post/create-post-payload';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  apiBaseUrl =  environment.apiRedditUrl;
  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<PostModel[]> {
    return this.http.get<PostModel[]>(this.apiBaseUrl + sharedConsts.getAllPostsUrl);
  }

  createPost(postPayload: CreatePostPayload): Observable<any> {
    return this.http.post<any>(this.apiBaseUrl, postPayload);

  }
}
