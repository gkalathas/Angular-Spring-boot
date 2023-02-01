import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Article} from '../article/article';

@Component({
  templateUrl: './my-user-test.component.html'
})
export class MyUserTestComponent implements OnInit {
  myArticle: Article;
  

  
  constructor(private http: HttpClient, private router: Router) {}
  
  ngOnInit() {
  }

  addArticle(newtitle: HTMLInputElement, newlink: HTMLInputElement) {
    let title = newtitle.value;
    let link = newlink.value;
    this.myArticle = new Article(0, title, link);
    console.log(newlink.value);
    console.log(newtitle.value);

  }
}
