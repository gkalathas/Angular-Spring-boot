import {ChangeDetectorRef, Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Subscription} from 'rxjs';
import {filter} from 'rxjs/operators';
import {ToitsuNavService} from './toitsu-nav.service';
import {AppComponent} from '../../app.component';
import {toitsuNavMapConsts} from './toitsu-nav-map.consts';

@Component({
  /* tslint:disable:component-selector */
  selector: '[app-toitsu-navitem]',
  /* tslint:enable:component-selector */
  template: `
    <ng-container>
      <a [attr.href]="item.url" (click)="itemClick($event)"
         *ngIf="(!item.routerLink || item.items) && item.visible !== false && (!item.needPermission || item.permission)"
         (mouseenter)="onMouseEnter()" (keydown.enter)="itemClick($event)"
         [attr.target]="item.target" [attr.tabindex]="0" pRipple>
        <i class="layout-menuitem-icon"  [ngClass]="item.icon"></i>
        <span>{{item.label}}</span>
        <span class="menuitem-badge" [ngClass]="item.badgeStyleClass" *ngIf="item.badge">{{item.badge}}</span>
        <i class="fa fa-fw fa-angle-down layout-menuitem-toggler" *ngIf="item.items"></i>
      </a>
      <a (click)="itemClick($event)" (mouseenter)="onMouseEnter()"
         *ngIf="(item.routerLink && !item.items) && item.visible !== false && (!item.needPermission || item.permission)"
         [routerLink]="item.routerLink" routerLinkActive="active-menuitem-routerlink"
         [routerLinkActiveOptions]="{exact: true}" [attr.target]="item.target" [attr.tabindex]="0" [ngClass]="item.class" pRipple>
        <i class="layout-menuitem-icon" [ngClass]="item.icon"></i>
        <span>{{item.label}}</span>
        <span class="menuitem-badge" [ngClass]="item.badgeStyleClass" *ngIf="item.badge">{{item.badge}}</span>
        <i class="fa fa-fw fa-angle-down layout-menuitem-toggler" *ngIf="item.items"></i>
      </a>
      <div class="submenu-arrow" *ngIf="item.items && item.visible !== false"></div>
      <ul *ngIf="(item.items && active) && item.visible !== false"
          [@children]="((app.isSlim()||app.isHorizontal()) && root) ? (active ? 'visible' : 'hidden') :
				(active ? 'visibleAnimated' : 'hiddenAnimated')">
        <ng-template ngFor let-child let-i="index" [ngForOf]="item.items">
          <li app-toitsu-navitem [item]="child" [index]="i" [parentKey]="key" [class]="child.badgeClass"></li>
        </ng-template>
      </ul>
    </ng-container>
  `,
  // tslint:disable-next-line:no-host-metadata-property
  host: {
    '[class.active-menuitem]': 'active'
  },
  animations: [
    trigger('children', [
      state('void', style({
        height: '0px',
        opacity: 0
      })),
      state('hiddenAnimated', style({
        height: '0px',
        opacity: 0
      })),
      state('visibleAnimated', style({
        height: '*',
        opacity: 1
      })),
      state('visible', style({
        height: '*',
        'z-index': 100,
        opacity: 1
      })),
      state('hidden', style({
        height: '0px',
        'z-index': '*',
        opacity: 0
      })),
      transition('visibleAnimated => hiddenAnimated', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')),
      transition('hiddenAnimated => visibleAnimated', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')),
      transition('void => visibleAnimated, visibleAnimated => void',
        animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'))
    ])
  ]
})
export class ToitsuNavitemComponent implements OnInit, OnDestroy {
  
  @Input() item: any;
  
  @Input() index: number;
  
  @Input() root: boolean;
  
  @Input() parentKey: string;
  
  active = false;
  
  menuSourceSubscription: Subscription;
  
  menuResetSubscription: Subscription;
  
  key: any;
  
  constructor(public app: AppComponent, public router: Router, private cd: ChangeDetectorRef,
              private toitsuNavService: ToitsuNavService) {
    this.menuSourceSubscription = this.toitsuNavService.menuSource$.subscribe(key => {
      // deactivate current active menu
      if (this.active && this.key !== key && key.indexOf(this.key) !== 0) {
        this.active = false;
      }
    });
    
    this.menuResetSubscription = this.toitsuNavService.resetSource$.subscribe(() => {
      this.active = false;
    });
    
    this.router.events.pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(params => {
        if (this.app.isSlim() || this.app.isHorizontal()) {
          this.active = false;
        } else {
          if (this.item.routerLink) {
            this.updateActiveStateFromRoute();
          } else {
            this.updateActiveStateForNonFinalItem();
          }
        }
      });
    
    this.toitsuNavService.navItems.push(this);
  }
  
  ngOnInit() {
    if (!(this.app.isSlim() || this.app.isHorizontal()) && this.item.routerLink) {
      this.updateActiveStateFromRoute();
    }
    
    this.key = this.parentKey ? this.parentKey + '-' + this.index : (this.index);
  }
  
  updateActiveStateFromRoute() {
    
    let routerUrl = this.router.url;
    if (this.router.url.indexOf('/view/') !== -1) {
      routerUrl = this.router.url.substr(0, this.router.url.lastIndexOf('/'));
    }
    
    if (!this.item.routerLink) {
      this.active = false;
    }
    else {
      this.active = this.router.isActive(this.item.routerLink[0], this.item.items ? false : true)
        || this.item.routerLink[0] === toitsuNavMapConsts[routerUrl];
    }
  }
  
  updateActiveStateForNonFinalItem() {
    // This only works for nested items in one level
    
    let routerUrl = this.router.url;
    if (this.router.url.indexOf('/view/') !== -1) {
      routerUrl = this.router.url.substr(0, this.router.url.lastIndexOf('/'));
    }
    
    for (let childItem of this.item.items) {
      if (childItem.routerLink) {
        if (this.router.isActive(childItem.routerLink[0], childItem.items ? false : true)
          || childItem.routerLink[0] === toitsuNavMapConsts[routerUrl]) {
          this.active = true;
        }
      }
    }
  }
  
  itemClick(event: Event) {
    // avoid processing disabled items
    if (this.item.disabled) {
      event.preventDefault();
      return;
    }
    
    // navigate with hover in horizontal mode
    if (this.root) {
      this.app.menuHoverActive = !this.app.menuHoverActive;
    }
    
    // notify other items
    this.toitsuNavService.onMenuStateChange(this.key);
    
    // execute command
    if (this.item.command) {
      this.item.command({originalEvent: event, item: this.item});
    }
    
    // toggle active state
    if (this.item.items) {
      this.active = !this.active;
    } else {
      // activate item
      this.active = true;
      
      // reset horizontal menu
      if (this.app.isSlim() || this.app.isHorizontal()) {
        this.toitsuNavService.reset();
      }
      
      this.app.overlayMenuActive = false;
      this.app.staticMenuMobileActive = false;
      this.app.menuHoverActive = !this.app.menuHoverActive;
    }
  }
  
  onMouseEnter() {
    // activate item on hover
    if (this.root && this.app.menuHoverActive && (this.app.isHorizontal() || this.app.isSlim()) && this.app.isDesktop()) {
      this.toitsuNavService.onMenuStateChange(this.key);
      this.active = true;
    }
  }
  
  ngOnDestroy() {
    if (this.menuSourceSubscription) {
      this.menuSourceSubscription.unsubscribe();
    }
    
    if (this.menuResetSubscription) {
      this.menuResetSubscription.unsubscribe();
    }
  }
}
