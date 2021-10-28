import {Action} from "@ngrx/store";
import {Product} from "../model/product.model";

export enum ProductActionTypes {
  DEFAULT = "[Product] Default product action",
  /** Get All products */
  GET_ALL_PRODUCTS = "[Product] Get All products",
  GET_ALL_PRODUCTS_SUCCESS = "[Product] Get All products Success",
  GET_ALL_PRODUCTS_ERROR = "[Product] Get All products Error",
  /** Get Selected products */
  GET_SELECTED_PRODUCTS = "[Product] Get selected products",
  GET_SELECTED_PRODUCTS_SUCCESS = "[Product] Get selected products Success",
  GET_SELECTED_PRODUCTS_ERROR = "[Product] Get selected products Error",
  /** Get Available products */
  GET_AVAILABLE_PRODUCTS = "[Product] Get available products",
  GET_AVAILABLE_PRODUCTS_SUCCESS = "[Product] Get available products Success",
  GET_AVAILABLE_PRODUCTS_ERROR = "[Product] Get available products Error",


  /** Search product */
  SEARCH_PRODUCTS = "[Product] Search product",
  SEARCH_PRODUCTS_SUCCESS = "[Product] Search product Success",
  SEARCH_PRODUCTS_ERROR = "[Product] Search product Error",


  /** Search product */
  SELECT_PRODUCT = "[Product] Select product",
  SELECT_PRODUCT_SUCCESS = "[Product] Select product Success",
  SELECT_PRODUCT_ERROR = "[Product] Select product Error",

  /** Delete product */
  DELETE_PRODUCT = "[Product] Delete product",
  DELETE_PRODUCT_SUCCESS = "[Product] Delete product Success",
  DELETE_PRODUCT_ERROR = "[Product] Delete product Error",


  /** New  product */
  NEW_PRODUCT = "[Product] New product",
  NEW_PRODUCT_SUCCESS = "[Product] New product Success",
  NEW_PRODUCT_ERROR = "[Product] New product Error",

  /** Save  product */
  SAVE_PRODUCT = "[Product] Save product",
  SAVE_PRODUCT_SUCCESS = "[Product] Save product Success",
  SAVE_PRODUCT_ERROR = "[Product] Save product Error",

  /** Edit  product */
  EDIT_PRODUCT = "[Product] Edit product",
  EDIT_PRODUCT_SUCCESS = "[Product] Edit product Success",
  EDIT_PRODUCT_ERROR = "[Product] Edit product Error",

  /** Update  product */
  UPDATE_PRODUCT = "[Product] Update product",
  UPDATE_PRODUCT_SUCCESS = "[Product] Update product Success",
  UPDATE_PRODUCT_ERROR = "[Product] Update product Error",


}

/** Get All products actions */
export class GetAllProductsAction implements Action {
  type: ProductActionTypes = ProductActionTypes.GET_ALL_PRODUCTS;

  constructor(public payload: any) {
  }
}

export class GetAllProductsActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.GET_ALL_PRODUCTS_SUCCESS;

  constructor(public payload: Product[]) {
  }
}

export class GetAllProductsActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.GET_ALL_PRODUCTS_ERROR;

  constructor(public payload: string) {
  }
}


/** Get Selected products actions */
export class GetSelectedProductsAction implements Action {
  type: ProductActionTypes = ProductActionTypes.GET_SELECTED_PRODUCTS;

  constructor(public payload: any) {
  }
}

export class GetSelectedProductsActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.GET_SELECTED_PRODUCTS_SUCCESS;

  constructor(public payload: Product[]) {
  }
}

export class GetSelectedProductsActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.GET_SELECTED_PRODUCTS_ERROR;

  constructor(public payload: string) {
  }
}

/** Get All products actions */
export class GetAvailableProductsAction implements Action {
  type: ProductActionTypes = ProductActionTypes.GET_AVAILABLE_PRODUCTS;

  constructor(public payload: any) {
  }
}

export class GetAvailableProductsActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.GET_AVAILABLE_PRODUCTS_SUCCESS;

  constructor(public payload: Product[]) {
  }
}

export class GetAvailableProductsActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.GET_AVAILABLE_PRODUCTS_ERROR;

  constructor(public payload: string) {
  }
}

/** Search products actions */
export class SearchProductsAction implements Action {
  type: ProductActionTypes = ProductActionTypes.SEARCH_PRODUCTS;

  constructor(public payload: string) {
  }
}

export class SearchProductsActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.SEARCH_PRODUCTS_SUCCESS;

  constructor(public payload: Product[]) {
  }
}

export class SearchProductsActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.SEARCH_PRODUCTS_ERROR;

  constructor(public payload: string) {
  }
}

/** Select products actions */
export class SelectProductAction implements Action {
  type: ProductActionTypes = ProductActionTypes.SELECT_PRODUCT;

  constructor(public payload: Product) {
  }
}

export class SelectProductActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.SELECT_PRODUCT_SUCCESS;

  constructor(public payload: Product) {
  }
}

export class SelectProductActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.SELECT_PRODUCT_ERROR;

  constructor(public payload: string) {
  }
}

/** Delete product actions */
export class DeleteProductAction implements Action {
  type: ProductActionTypes = ProductActionTypes.DELETE_PRODUCT;

  constructor(public payload: Product) {
  }
}

export class DeleteProductActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.DELETE_PRODUCT_SUCCESS;

  constructor(public payload: Product) {
  }
}

export class DeleteProductActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.DELETE_PRODUCT_ERROR;

  constructor(public payload: string) {
  }
}


/** New  product actions*/
export class NewProductAction implements Action {
  type: ProductActionTypes = ProductActionTypes.NEW_PRODUCT;

  constructor(public payload: any) {
  }
}

export class NewProductActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.NEW_PRODUCT_SUCCESS;

  constructor(public payload: any) {
  }
}

export class NewProductActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.NEW_PRODUCT_ERROR;

  constructor(public payload: string) {
  }
}


/** Save  product actions*/
export class SaveProductAction implements Action {
  type: ProductActionTypes = ProductActionTypes.SAVE_PRODUCT;

  constructor(public payload: Product) {
  }
}

export class SaveProductActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.SAVE_PRODUCT_SUCCESS;

  constructor(public payload: any) {
  }
}

export class SaveProductActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.SAVE_PRODUCT_ERROR;

  constructor(public payload: string) {
  }
}


/** Edit  product actions*/
export class EditProductAction implements Action {
  type: ProductActionTypes = ProductActionTypes.EDIT_PRODUCT;

  constructor(public payload: number) {
  }
}

export class EditProductActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.EDIT_PRODUCT_SUCCESS;

  constructor(public payload: Product) {
  }
}

export class EditProductActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.EDIT_PRODUCT_ERROR;

  constructor(public payload: string) {
  }
}

/** Update  product actions*/

export class UpdateProductAction implements Action {
  type: ProductActionTypes = ProductActionTypes.UPDATE_PRODUCT;

  constructor(public payload: Product) {
  }
}

export class UpdateProductActionSuccess implements Action {
  type: ProductActionTypes = ProductActionTypes.UPDATE_PRODUCT_SUCCESS;

  constructor(public payload: Product) {
  }
}

export class UpdateProductActionError implements Action {
  type: ProductActionTypes = ProductActionTypes.UPDATE_PRODUCT_ERROR;

  constructor(public payload: string) {
  }
}


/** declaration type for all this actions **/
export type ProductsActions = GetAllProductsAction | GetAllProductsActionSuccess | GetAllProductsActionError
  | GetSelectedProductsAction | GetSelectedProductsActionSuccess | GetSelectedProductsActionError
  | GetAvailableProductsAction | GetAvailableProductsActionSuccess | GetAvailableProductsActionError
  | SearchProductsAction | SearchProductsActionSuccess | SearchProductsActionError
  | SelectProductAction | SelectProductActionSuccess | SelectProductActionError
  | DeleteProductAction | DeleteProductActionSuccess | DeleteProductActionError
  | NewProductAction | NewProductActionSuccess | NewProductActionError
  | SaveProductAction | SaveProductActionSuccess | SaveProductActionError
  | EditProductAction | EditProductActionSuccess | EditProductActionError
  | UpdateProductAction | UpdateProductActionSuccess | UpdateProductActionError

  ;
