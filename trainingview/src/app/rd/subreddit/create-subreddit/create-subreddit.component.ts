import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {SubredditService} from '../../shared/subreddit.service';
import {SubredditModel} from '../../shared/subreddit-model';

@Component({
  selector: 'app-create-subreddit',
  templateUrl: './create-subreddit.component.html',
  styleUrls: ['./create-subreddit.component.scss']
})
export class CreateSubredditComponent implements OnInit {
  createSubredditForm: FormGroup;
  subredditModel: SubredditModel;
  title = new FormControl('');
  description = new FormControl('');



  constructor(private router: Router, private subredditService: SubredditService) {
    this.createSubredditForm = new FormGroup({
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });
    // this.subredditModel = {
    //   name: '',
    //   description: '',
    //   postCount: 0,
    //
    // };
  }

  ngOnInit(): void {
  }

  discard() {
    this.router.navigateByUrl('/').then(r => console.log(r));
  }

  createSubreddit() {

  }
}
