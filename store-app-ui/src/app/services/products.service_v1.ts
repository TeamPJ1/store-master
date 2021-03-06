import {Injectable} from "@angular/core";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Product} from "../model/product.model";
import {FormGroup} from "@angular/forms";

@Injectable({providedIn: "root"}) // disponible dans tout root sinon il faut le declarer dans le module
export class ProductsService {
  serviceUrl?: String | null = null;
  constructor(private  http: HttpClient) {
    // this.host = (Math.random() > 0.2) ? environment.host : environment.unreachableHost;
    this.serviceUrl = environment.host;
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.serviceUrl  + "/api/products")
  }

  getSelectedProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.serviceUrl  + "/products?selected=true");
  }

  getAvailableProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.serviceUrl  + "/products?available=true");
  }

  searchProducts(keyword: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.serviceUrl  + "/products?name_like=" + keyword);
  }

  selectProduct(product: Product): Observable<Product> {
    // let product.selected = !product.selected;
    return this.http.put<Product>(this.serviceUrl  + "/products/" + product.id, {...product, selected: !product.selected});//copie of product immutable car il est dans le store
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(this.serviceUrl  + "/products/" + id);
  }

  saveProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.serviceUrl  + "/products/", product);
  }

  updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(this.serviceUrl  + "/products/" + product.id, product);
  }

  deleteProduct(product: Product): Observable<void> {
    return this.http.delete<void>(this.serviceUrl  + "/products/" + product.id);
  }

  getHeaders() {
    const headers = new Headers();
    // headers.append('Access-Control-Allow-Headers', 'Content-Type');
    // headers.append('Access-Control-Allow-Methods', 'GET');
    headers.append('Access-Control-Allow-Origin', '*');

    return headers;
  }
}
