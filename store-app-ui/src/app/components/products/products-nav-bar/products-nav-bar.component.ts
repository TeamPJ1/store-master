import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {
  GetAllProductsAction,
  GetAvailableProductsAction,
  GetSelectedProductsAction, SearchProductsAction, ProductActionTypes
} from "../../../ngrx/products.actions";
import {Router} from "@angular/router";
import {ProductsState} from "../../../ngrx/products.reducer";

@Component({
  selector: 'app-products-nav-bar',
  templateUrl: './products-nav-bar.component.html',
  styleUrls: ['./products-nav-bar.component.css']
})
export class ProductsNavBarComponent implements OnInit {

  productState:ProductsState |null=null;
  readonly  ProductActionTypes= ProductActionTypes;
  constructor(private store: Store<any>, private router: Router) {
  }

  ngOnInit(): void {
    this.store.subscribe(state => {
      this.productState = state.catalogState;
    });
    this.onGetAllProducts();
  }

  onGetAllProducts() {
    this.store.dispatch(new GetAllProductsAction({}));
  }

  onGetSelectedProducts() {
    this.store.dispatch(new GetSelectedProductsAction({}));
  }

  onGetAvailableProducts() {
    this.store.dispatch(new GetAvailableProductsAction({}));
  }

  onNewProduct() {
    this.router.navigateByUrl("/newProduct");
  }

  onSearchProduct(dataForm: any) {
    this.store.dispatch(new SearchProductsAction(dataForm.keyword));
  }
}
