import { Component, OnInit } from '@angular/core';
import {PostModel} from '../shared/post/post-model';
import {PostService} from '../shared/post/post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  // post: PostModel = new PostModel();
  posts: PostModel[] = [];
  constructor(private postService: PostService) {

    this.postService.getAllPosts().subscribe(post => {
      this.posts = post;
    });
  }

  ngOnInit(): void {


  }

}
