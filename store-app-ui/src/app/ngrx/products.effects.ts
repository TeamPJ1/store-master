import {Injectable} from "@angular/core";
import {ProductsService} from "../services/products.service";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {Observable, of} from "rxjs";
import {Action} from "@ngrx/store";
import {
  DeleteProductActionError,
  DeleteProductActionSuccess, EditProductActionError, EditProductActionSuccess,
  GetAllProductsActionError,
  GetAllProductsActionSuccess,
  GetAvailableProductsActionError,
  GetAvailableProductsActionSuccess,
  GetSelectedProductsActionSuccess, NewProductActionSuccess,
  ProductActionTypes,
  ProductsActions, SaveProductActionError, SaveProductActionSuccess,
  SearchProductsActionError,
  SearchProductsActionSuccess,
  SelectProductActionError,
  SelectProductActionSuccess, UpdateProductActionError, UpdateProductActionSuccess
} from "./products.actions";
import {catchError, map, mergeMap} from "rxjs/operators";

@Injectable()
export class ProductsEffects {

  constructor(private productsService: ProductsService, private effectActions: Actions) {
  }

  /** NB: the effects call rest service and dispatch success or error event after response **/
  /**  Get selected products **/
  getAllProductsEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.GET_ALL_PRODUCTS),
      mergeMap((action: ProductsActions) => {
        return this.productsService.getAllProducts().pipe(
          map(response => {
            console.log(response._embedded.products);
            return new GetAllProductsActionSuccess(response._embedded.products);
          }),
          catchError((err) => {
            console.log(err);
           return  of(new GetAllProductsActionError(err));
          })
        )
      })
    )
  );

  /** Get selected products **/
  getSelectedProductsEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.GET_SELECTED_PRODUCTS),
      mergeMap((action: ProductsActions) => {
        return this.productsService.getSelectedProducts().pipe(
          map(products => new GetSelectedProductsActionSuccess(products)),
          catchError((err) => of(new GetSelectedProductsActionSuccess(err)))
        )
      })
    )
  );

  /** Get Available products **/
  getAvailableProductsEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.GET_AVAILABLE_PRODUCTS),
      mergeMap((action: ProductsActions) => {
        return this.productsService.getAvailableProducts().pipe(
          map(response => new GetAvailableProductsActionSuccess(response._embedded.products)),
          catchError((err) => of(new GetAvailableProductsActionError(err)))
        )
      })
    )
  );

  /** Search products **/
  searchProductsEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.SEARCH_PRODUCTS),
      mergeMap((action: ProductsActions) => {
        return this.productsService.searchProducts(action.payload).pipe(
          map(response => new SearchProductsActionSuccess(response._embedded.products)),
          catchError((err) => of(new SearchProductsActionError(err)))
        )
      })
    )
  );

  /** Select product **/
  selectProductEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.SELECT_PRODUCT),
      mergeMap((action: ProductsActions) => {
        return this.productsService.selectProduct(action.payload).pipe(
          map(product => new SelectProductActionSuccess(product)),
          catchError((err) => of(new SelectProductActionError(err)))
        )
      })
    )
  );

  /** Delete product **/
  deleteProductEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.DELETE_PRODUCT),
      mergeMap((action: ProductsActions) => {
        return this.productsService.deleteProduct(action.payload).pipe(
          map(() => new DeleteProductActionSuccess(action.payload)),
          catchError((err) => of(new DeleteProductActionError(err)))
        )
      })
    )
  );

  /** New product **/
  newProductEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.NEW_PRODUCT),
      map((action: ProductsActions) => {
        return new NewProductActionSuccess({});
      })
    )
  );

  /** Save product **/
  saveProductEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.SAVE_PRODUCT),
      mergeMap((action: ProductsActions) => {
        return this.productsService.saveProduct(action.payload).pipe(
          map(product => new SaveProductActionSuccess(product)),
          catchError((err) => of(new SaveProductActionError(err)))
        )
      })
    )
  );

  /** Edit product **/
  editProductEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.EDIT_PRODUCT),
      mergeMap((action: ProductsActions) => {
        return this.productsService.getProduct(action.payload).pipe(
          map(product => new EditProductActionSuccess(product)),
          catchError((err) => of(new EditProductActionError(err.message)))
        )
      })
    )
  );

  /** Update product **/
  updateProductEffect: Observable<ProductsActions> = createEffect(
    () => this.effectActions.pipe(
      ofType(ProductActionTypes.UPDATE_PRODUCT),
      mergeMap((action: ProductsActions) => {
        return this.productsService.updateProduct(action.payload).pipe(
          map(product => new UpdateProductActionSuccess(product)),
          catchError((err) => of(new UpdateProductActionError(err)))
        )
      })
    )
  );


}
