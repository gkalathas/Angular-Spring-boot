import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {gpRouting} from '../gp/gp.routing';
import {ToitsuSharedModule} from '../toitsu-shared/toitsu-shared.module';
import { ProductListComponent } from './product/product-list/product-list.component';
import {CdRouting} from './cd.routing';
import { AddEditProductComponent } from './product/add-edit-product/add-edit-product.component';
import {ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    ProductListComponent,
    AddEditProductComponent
  ],
  exports: [],
  imports: [
    RouterModule.forChild(CdRouting),
    ToitsuSharedModule,
    ReactiveFormsModule

  ]

})
export class CdModule {
  
}
