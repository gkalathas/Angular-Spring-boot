import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProductService} from '../../product-service';
import {ProductModel} from '../../product-model';
import {TranslateService} from '@ngx-translate/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit, OnDestroy {


  first = 0;
  //
  rows = 10;
  selectedProduct: ProductModel;

  subscriptions: Subscription[] = [];

  subscription: Subscription;

  displayAddModal: boolean = false;

  products: ProductModel[];
  constructor(private productService: ProductService, private translateService: TranslateService,
              private confirmationService: ConfirmationService, private messageService: MessageService) { }

  ngOnInit(): void {

    this.getProductList();
  }

  getProductList() {
    this.subscription = this.productService.getProducts().subscribe(data => {
      this.products = data;
      console.log(data);
    });

    this.subscriptions.push(this.subscription);
  }


  shoAddModal() {
    this.displayAddModal = true;
    this.selectedProduct = null;
  }


  hideAddModal(isClosed: boolean) {
    this.displayAddModal = !isClosed;
  }

  showEditProduct(product: ProductModel) {
    this.displayAddModal = true;
    this.selectedProduct = product;
  }

  saveProductToList(newData: any) {

    // if (this.selectedProduct && newData.id === this.selectedProduct.id) {
    //   const productIndex = this.products.findIndex((data => data.id === newData.id);
    //   this.products[productIndex] = newData;
    // } else {
    //   this.products.unshift(newData);
    // }
    this.getProductList();
  }

  deleteProduct(product: ProductModel) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this product?',
      accept: () => {
        this.subscription = this.productService.deleteProduct(product.id).subscribe(data => {
          console.log(data);
          this.messageService.add({severity: 'success', summary: 'success', detail: 'Product deleted'});
          this.getProductList();
        },
          error => {
            console.log('error occurred');
            this.messageService.add({severity: 'error', summary: 'Error', detail: error});
          });
      }
    });
    this.subscriptions.push(this.subscription);
  }

  ngOnDestroy(): void {

    this.subscriptions.forEach(sub => sub.unsubscribe());
  }


  paginate($event: any) {

  }
}


//
// next() {
//   this.first = this.first + this.rows;
// }
//
// prev() {
//   this.first = this.first - this.rows;
// }
//
// reset() {
//   this.first = 0;
// }
//
// isLastPage(): boolean {
//   return this.products ? this.first === (this.products.length - this.rows) : true;
// }
//
// isFirstPage(): boolean {
//   return this.products ? this.first === 0 : true;
// }
