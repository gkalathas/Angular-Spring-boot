import { Component, OnInit } from '@angular/core';
import {SubredditModel} from '../../shared/subreddit-model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CreatePostPayload} from './create-post-payload';
import {Router} from '@angular/router';
import {SubredditService} from '../../shared/subreddit.service';
import {PostService} from '../../shared/post/post.service';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
  subreddits: SubredditModel[] = [];
  postPayload: CreatePostPayload;
  createPostForm: FormGroup;

  constructor(private router: Router,
              private subredditService: SubredditService,
              private postService: PostService) {

    this.postPayload = {
      postName: '',
      url: '',
      description: '',
      subredditName: ''
    };
  }

  ngOnInit(): void {
    this.createPostForm = new FormGroup({
      postName: new FormControl('', Validators.required),
      subredditName: new FormControl('', Validators.required),
      url: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
    });
    this.subredditService.getAllSubreddits().subscribe((data) => {
      this.subreddits = data;
    }, error => {
      throwError(error);
    });
  }
  createPost() {
    this.postPayload.postName = this.createPostForm.get('postName').value;
    this.postPayload.subredditName = this.createPostForm.get('subredditName').value;
    this.postPayload.url = this.createPostForm.get('url').value;
    this.postPayload.description = this.createPostForm.get('description').value;

    this.postService.createPost(this.postPayload).subscribe((data) => {
      this.router.navigateByUrl('/').then(r => console.log(r));
    }, error => {
      throwError(error);
    });
  }

  discardPost() {
    this.router.navigateByUrl('/').then(r => console.log(r));
  }

}
