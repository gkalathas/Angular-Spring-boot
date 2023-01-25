import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductModel} from './product-model';
import {environment} from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiServerUrl = environment.newUrl;

  // private apiServerUrl = 'http://localhost:8080/';

  constructor(private httpClient: HttpClient) { }


  loadData($event: any) {
    return this.httpClient.get(`${this.apiServerUrl}/products/getAll?page=${$event.first / $event.rows}&size=${$event.rows}`);
  }
  getProducts(): Observable<ProductModel[]> {
    return this.httpClient.get<ProductModel[]>(`${this.apiServerUrl}/products/getAll`);
  }


  saveProduct(product: ProductModel): Observable<ProductModel> {

      return this.httpClient.post<ProductModel>(`${this.apiServerUrl}/products/saveProduct`, product);
  }

  updateProduct(id: number,  product: ProductModel): Observable<ProductModel> {
    return this.httpClient.put<ProductModel>(`${this.apiServerUrl}/update/${id}`, product);
  }

  // tslint:disable-next-line:ban-types
  deleteProduct(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.apiServerUrl}/delete/{id}`);
  }

}

