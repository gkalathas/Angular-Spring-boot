import {APP_INITIALIZER, Injector, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {appRoutes} from './app-routing.module';
import {TranslateLoader, TranslateModule, TranslateService} from '@ngx-translate/core';
import {toitsuTranslateLoader} from './toitsu-shared/toitsu-translate/toitsu-translate-loader';
import {toitsuTranslateInitializer} from './toitsu-shared/toitsu-translate/toitsu-translate-initializer';
import {ConfirmationService, MessageService} from 'primeng/api';
import {DialogService} from 'primeng/dynamicdialog';
import {JsonPipe} from '@angular/common';
import {ToitsuSharedModule} from './toitsu-shared/toitsu-shared.module';

import {AppComponent} from './app.component';
import {ToitsuNavComponent} from './toitsu-layout/toitsu-nav/toitsu-nav.component';
import {ToitsuNavitemComponent} from './toitsu-layout/toitsu-nav/toitsu-navitem.component';
import {ToitsuBreadcrumbComponent} from './toitsu-layout/toitsu-breadcrumb/toitsu-breadcrumb.component';
import {ToitsuHeaderComponent} from './toitsu-layout/toitsu-header/toitsu-header.component';
import {ToitsuFooterComponent} from './toitsu-layout/toitsu-footer/toitsu-footer.component';
import {IndexComponent} from './toitsu-layout/index/index.component';
import {SaModule} from './sa/sa.module';
import {CdModule} from './cd/cd.module';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [

    AppComponent,
    ToitsuNavComponent,
    ToitsuNavitemComponent,
    ToitsuHeaderComponent,
    ToitsuFooterComponent,
    ToitsuBreadcrumbComponent,
    IndexComponent
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (toitsuTranslateLoader),
        deps: [HttpClient]
      }
    }),
    ToitsuSharedModule,
    SaModule,
    CdModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: toitsuTranslateInitializer,
      deps: [TranslateService, Injector],
      multi: true
    },
    MessageService,
    ConfirmationService,
    DialogService,
    JsonPipe
  ],
  exports: [
    ToitsuFooterComponent,
    ToitsuHeaderComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
