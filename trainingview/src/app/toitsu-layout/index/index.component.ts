import {Component, AfterViewInit} from '@angular/core';

@Component({
  templateUrl: './index.component.html'
})
export class IndexComponent implements AfterViewInit {
  subtitle: string;
  
  constructor() {
    this.subtitle = 'This is the Toitsu NG main page.';
  }
  
  ngAfterViewInit() {
  }
}
