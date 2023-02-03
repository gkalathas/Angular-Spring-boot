import {Component, Input, OnInit} from '@angular/core';
import {PostModel} from '../post/post-model';

@Component({
  selector: 'app-post-tile',
  templateUrl: './post-tile.component.html',
  styleUrls: ['./post-tile.component.scss']
})
export class PostTileComponent implements OnInit {

  @Input() data: PostModel[];


  constructor() { }

  ngOnInit(): void {
  }

  goToPost(id: number) {

  }
}
