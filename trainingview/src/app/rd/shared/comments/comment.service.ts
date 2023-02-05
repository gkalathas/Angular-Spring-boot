import { Injectable } from '@angular/core';
import {CommentPayload} from './comment-payload';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {commentConsts} from './commentConsts';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  apiBaseUrl = environment.apiRedditUrl;
  constructor(private http: HttpClient) { }

  getAllCommentsForPost(postId: number): Observable<CommentPayload[]> {
    return this.http.get<CommentPayload[]>(this.apiBaseUrl + commentConsts.getCommentsByPostUrl + postId);
  }

  postComment(commentPayload: CommentPayload): Observable<any> {
    return this.http.post<any>(this.apiBaseUrl + commentConsts.postCommentUrl, commentPayload);
  }
}
