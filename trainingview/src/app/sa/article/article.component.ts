import {Component, EventEmitter, HostBinding, Input, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {Article} from './article';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

  @Input()article: Article;
  // @HostBinding('attr.class') cssClass = 'row';
  articles: Article[] = [
    new Article(10, 'angular' , 'https://angular.io'),
    new Article(30, 'Fullstack', 'https://fullstack.io'),
    new Article(0, 'Angular Homepage', 'https://angular.io')];
  @Input() viewLink: string;
  @Output() rowClicked = new EventEmitter<{}>();
  @Output() rowDblClicked = new EventEmitter<{}>();

  @Input() selectionMode: string = 'single';
  @Output() rowSelected = new EventEmitter<{}>();
  @Output() rowUnselected = new EventEmitter<{}>();


  constructor(private router: Router) {

  }

  ngOnInit(): void {

  }

  onRowSelect(rowData) {
    this.rowSelected.emit(rowData);
  }

  onRowUnselect(rowData) {
    this.rowUnselected.emit(rowData);
  }


  onRowClick(rowData) {
    this.rowClicked.emit(rowData);
    console.log(rowData);
  }

  onRowDblClick(rowData) {
    this.rowDblClicked.emit(rowData);

    if (this.viewLink) {
      this.router.navigate([this.viewLink, rowData.id]);
    }
  }

  voteUp() {

     this.article.voteUp();
  }

  voteDown() {
    this.article.voteDown();
  }


}
