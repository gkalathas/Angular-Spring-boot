import {Component, Input, OnInit} from '@angular/core';
import {PostModel} from '../post/post-model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-post-tile',
  templateUrl: './post-tile.component.html',
  styleUrls: ['./post-tile.component.scss']
})
export class PostTileComponent implements OnInit {

  @Input() posts: PostModel[] = [];


  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToPost(id: number) {
    this.router.navigateByUrl('/rd/reddit/view-post/' + id).then(r => console.log(r));
  }
}
