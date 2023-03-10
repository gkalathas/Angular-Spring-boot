import { Component, OnInit } from '@angular/core';
import {SubredditModel} from '../../shared/subreddit-model';
import {SubredditService} from '../../shared/subreddit.service';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-list-subreddits',
  templateUrl: './list-subreddits.component.html',
  styleUrls: ['./list-subreddits.component.scss']
})
export class ListSubredditsComponent implements OnInit {
  subreddits: SubredditModel[] = [];

  constructor(private subredditService: SubredditService) { }


  ngOnInit(): void {
    this.subredditService.getAllSubreddits().subscribe(data => {
      this.subreddits = data;
    }, error => {
      throwError(error);
    });

  }

}
