import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProductService} from '../../product-service';
import {ProductModel} from '../../product-model';
import {TranslateService} from '@ngx-translate/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {Subscription} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment.staging';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit, OnDestroy {

  private apiServerUrl = environment.apiBaseUrl;
  selectedProduct: ProductModel;

  subscriptions: Subscription[] = [];

  subscription: Subscription;

  displayAddModal: boolean = false;

  products: ProductModel[];
  itemsPerPage: number = 10;
  constructor(private productService: ProductService, private translateService: TranslateService,
              private confirmationService: ConfirmationService, private messageService: MessageService,
              private http: HttpClient) { }

  ngOnInit(): void {


    this.loadProducts({first: 0, rows: this.itemsPerPage});
    // this.fetchProducts({first: 0, rows: this.itemsPerPage});
   // this.subscription = this.productService.loadData({first: 0, rows: this.itemsPerPage}).subscribe(
   //    response => {
   //      this.products = response['content'];
   //    }
   //  );

  //   this.getProductList();
  }

  loadProducts(event: any) {
    this.productService.loadData(event).subscribe(
      response => {
        this.products = response['content'];
      }
    );
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


    // this.getProductList();
  }

  deleteProduct(product: ProductModel) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this product?',
      accept: () => {
        this.subscription = this.productService.deleteProduct(product.id).subscribe(data => {
          console.log(data);
          this.messageService.add({severity: 'success', summary: 'success', detail: 'Product deleted'});
          // this.getProductList();
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


  // loadData($event: any) {
  //   this.http.get(`${this.apiServerUrl}/products/getAll?page=${$event.first / $event.rows}&size=${$event.rows}`)
  //     .subscribe(response => {
  //       this.products = response['content'];
  //     });
  // }

  fetchProducts($event: any) {
    this.http.get<ProductModel>(`${this.apiServerUrl}/products/getAll?page=${$event.first / $event.rows}&size=${$event.rows}`)
      .pipe(map(response => {
          const productArray: ProductModel[] = [];
          for (const key in response) {
            if (response.hasOwnProperty(key)) {
              productArray.push({...productArray[key], id: key});
            }
          }
          return productArray['content'];
        }
      )).subscribe(data => {
        console.log(data);
    });
  }
}
