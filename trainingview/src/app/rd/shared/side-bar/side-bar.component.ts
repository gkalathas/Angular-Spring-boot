import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToCreateSubreddit() {

    this.router.navigateByUrl('/rd/reddit/create-subreddit')
      .then(r => console.log(r));
  }

  goToCreatePost() {
    this.router.navigateByUrl('/rd/reddit/create-post')
      .then(r => console.log(r));
  }
}
