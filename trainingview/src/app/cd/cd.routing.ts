
import {ProductListComponent} from './product/product-list/product-list.component';
import {AddEditProductComponent} from './product/add-edit-product/add-edit-product.component';


export const CdRouting = [
  {
    path: 'product', children: [
      {path: 'list', component: ProductListComponent, data: {title: 'cd.product', breadcrumbs: [
            {label: 'cd.product', routerLink: ['/cd/product/list']}], permissions: []}
      },
      {path: 'addProduct', component: AddEditProductComponent, data: {title: 'cd.addProduct', breadcrumbs: [
            {label: 'cd.addProduct', routerLink: ['/cd/product/addProduct']}], permissions: []}
      }
    ]
  }
  
];
