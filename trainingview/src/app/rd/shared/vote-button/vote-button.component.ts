import {Component, Input, OnInit} from '@angular/core';
import {PostModel} from '../post/post-model';
import {VotePayload} from '../vote-payload';
import {throwError} from 'rxjs';
import {VoteType} from '../vote-type';
import {VoteService} from '../vote.service';
import {AuthService} from '../../auth/shared/auth.service';
import {PostService} from '../post/post.service';
import {ToitsuToasterService} from '../../../toitsu-shared/toitsu-toaster/toitsu-toaster.service';


@Component({
  selector: 'app-vote-button',
  templateUrl: './vote-button.component.html',
  styleUrls: ['./vote-button.component.scss']
})
export class VoteButtonComponent implements OnInit {

  @Input() post: PostModel;
  votePayload: VotePayload;
  upvoteColor: string;
  downvoteColor: string;
  constructor(private voteService: VoteService,
              private authService: AuthService,
              private postService: PostService, private toitsuToaster: ToitsuToasterService) {

    this.votePayload = {
      voteType: undefined,
      postId: undefined
    };
  }

  ngOnInit(): void {
    this.updateVoteDetails();
  }

  upvotePost() {
    this.votePayload.voteType = VoteType.UPVOTE;
    this.vote();
    this.downvoteColor = '';
  }

  downvotePost() {
    this.votePayload.voteType = VoteType.DOWNVOTE;
    this.vote();
    this.upvoteColor = '';
  }

  private vote() {
    this.votePayload.postId = this.post.id;
    this.voteService.vote(this.votePayload).subscribe(() => {
      this.updateVoteDetails();
    }, error => {
      this.toitsuToaster.showErrorStay(error.error.message);
      throwError(error);
    });
  }

  private updateVoteDetails() {
    this.postService.getPost(this.post.id).subscribe(post => {
      this.post = post;
    });
  }
}
