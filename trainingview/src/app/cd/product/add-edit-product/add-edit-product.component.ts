import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductService} from '../../product-service';
import {MessageService} from 'primeng/api';
import {ProductModel} from '../../product-model';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-add-edit-product',
  templateUrl: './add-edit-product.component.html',
  styleUrls: ['./add-edit-product.component.scss']
})
export class AddEditProductComponent implements OnInit, OnChanges, OnDestroy {
  @Input()displayAddModal: boolean = true;
  @Input() selectedProduct: ProductModel = null;
  @Output()clickClose: EventEmitter<boolean> = new EventEmitter<boolean>();
  modalType: string;

  subscriptions: Subscription[] = [];

  subscription: Subscription;
  id: number;
  productForm: FormGroup;

  constructor(private fb: FormBuilder,
              private productService: ProductService,
              private messageService: MessageService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  ngOnChanges(): void {

    if (this.selectedProduct) {
      this.modalType = 'Edit';
      this.id = +this.route.snapshot.params['id'];
      this.productForm.patchValue(this.selectedProduct);

    } else {
      this.productForm.reset();
      this.modalType = 'Add';
    }
  }


  initializeForm() {
    this.productForm = this.fb.group({
      title: ['', Validators.required],
      price: [0, Validators.required],
      description: [''],
      pcategory: ['', Validators.required],
      image: ['', Validators.required]
    });

  }
  closeModal() {
    this.clickClose.emit(true);
  }

  addProduct() {
    this.subscription = this.productService.saveProduct(this.productForm.value).subscribe(
      response => {
        console.log(response);
        this.productForm.reset();
        this.clickClose.emit(true);
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Product Added'});
      },
      error => {
        console.log('error occurred');
        this.messageService.add({severity: 'error', summary: 'Error', detail: error});
      }
    );
    this.subscriptions.push(this.subscription);
  }

  onUpdateProduct() {
    this.subscription = this.productService.updateProduct(this.id, this.selectedProduct).subscribe(
      response => {
        console.log(response);
        this.productForm.reset();
        this.clickClose.emit(true);
        this.messageService.add({severity: 'success', summary: 'success', detail: 'Product Updated'});
      },
      error => {
        console.log('error occurred');
        this.messageService.add({severity: 'error', summary: 'Error', detail: error});
      }
    );
    this.subscriptions.push(this.subscription);
  }

  ngOnDestroy(): void {

    this.subscriptions.forEach(sub => sub.unsubscribe());
  }


}
