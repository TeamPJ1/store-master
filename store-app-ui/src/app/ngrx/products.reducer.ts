import {Product} from "../model/product.model";
import {ProductActionTypes, ProductsActions} from "./products.actions";
import {Action} from "@ngrx/store";

export enum ProductStateEnum {
  LOADING = "Loading",
  LOADED = "Loaded",
  ERROR = "Error",
  INITIAL = "Initial",
  NEW = "New", // new pour s'assurer que les données sur formumaire sont prete pour afficher le formulaire
  EDIT = "EDIT"
}

export interface ProductsState {
  products: Product[],
  errorMessage: string,
  dataState: ProductStateEnum,
  currentProduct: Product |null,
  currentAction: ProductsActions|null,
}

// state initial
const initState: ProductsState = {
  products: [],
  errorMessage: "",
  dataState: ProductStateEnum.INITIAL,
  currentProduct: null,
  currentAction : null
}

// function reducer
export function productsReducer(state = initState, action: Action): ProductsState {
  switch (action.type) {
    /**  Get All products */
    case ProductActionTypes.GET_ALL_PRODUCTS:// dispatched by IHM
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};// ...object : copie de object state car il est immuatble
    case ProductActionTypes.GET_ALL_PRODUCTS_SUCCESS:// dispatched by effect ( after response of server/backend)
      return {...state, dataState: ProductStateEnum.LOADED, products: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};
    case ProductActionTypes.GET_ALL_PRODUCTS_ERROR:// dispatched by effect ( after response of backend)
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};
    /**  Get Selected products */
    case ProductActionTypes.GET_SELECTED_PRODUCTS:
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};
    case ProductActionTypes.GET_SELECTED_PRODUCTS_SUCCESS:
      return {...state, dataState: ProductStateEnum.LOADED, products: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};
    case ProductActionTypes.GET_SELECTED_PRODUCTS_ERROR:
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};
    /**  Get Available products */
    case ProductActionTypes.GET_AVAILABLE_PRODUCTS:
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};
    case ProductActionTypes.GET_AVAILABLE_PRODUCTS_SUCCESS:
      return {...state, dataState: ProductStateEnum.LOADED, products: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};
    case ProductActionTypes.GET_AVAILABLE_PRODUCTS_ERROR:
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};

    /**   Search products */
    case ProductActionTypes.SEARCH_PRODUCTS:
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};
    case ProductActionTypes.SEARCH_PRODUCTS_SUCCESS:
      return {...state, dataState: ProductStateEnum.LOADED, products: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};
    case ProductActionTypes.SEARCH_PRODUCTS_ERROR:
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};

    /**   Select product */
    case ProductActionTypes.SELECT_PRODUCT:
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};
    case ProductActionTypes.SELECT_PRODUCT_SUCCESS:
      // replace updated product in list of products
      let product = (<ProductsActions>action).payload;
      let listProducts = [...state.products];
      let data: Product[] = listProducts.map(p => p.id == product.id ? product : p);
      return {...state, dataState: ProductStateEnum.LOADED, products: data, currentAction:<ProductsActions>action};
    case ProductActionTypes.SELECT_PRODUCT_ERROR:
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};

    /**   Delete product */
    case ProductActionTypes.DELETE_PRODUCT:
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};
    case ProductActionTypes.DELETE_PRODUCT_SUCCESS:
      // delete product from list of products
      let p: Product = (<ProductsActions>action).payload;
      let index = state.products.indexOf(p);
      let products = [...state.products];
      let datalist: Product[] = products.slice(index, 1);
      return {...state, dataState: ProductStateEnum.LOADED, products: datalist, currentAction:<ProductsActions>action};
    case ProductActionTypes.DELETE_PRODUCT_ERROR:
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};


    /**   New product */
    case ProductActionTypes.NEW_PRODUCT:
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};
    case ProductActionTypes.NEW_PRODUCT_SUCCESS:
      return {...state, dataState: ProductStateEnum.NEW, currentAction:<ProductsActions>action};
    case ProductActionTypes.NEW_PRODUCT_ERROR:
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};

    /**   Save product */
    case ProductActionTypes.SAVE_PRODUCT:
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};
    case ProductActionTypes.SAVE_PRODUCT_SUCCESS:
      // add new product to list of products
      let prods: Product[] = [...state.products];
      prods.push((<ProductsActions>action).payload);
      return {...state, dataState: ProductStateEnum.LOADED, products: prods, currentAction:<ProductsActions>action};
    case ProductActionTypes.SAVE_PRODUCT_ERROR:
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};

    /**   Edit product */
    case ProductActionTypes.EDIT_PRODUCT:
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};
    case ProductActionTypes.EDIT_PRODUCT_SUCCESS:
      return {...state, dataState: ProductStateEnum.EDIT, currentProduct: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};
    case ProductActionTypes.EDIT_PRODUCT_ERROR:
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};

    /**   Update product */
    case ProductActionTypes.UPDATE_PRODUCT:
      return {...state, dataState: ProductStateEnum.LOADING, currentAction:<ProductsActions>action};
    case ProductActionTypes.UPDATE_PRODUCT_SUCCESS:
      // replace updated product in list of products
      let updatedProduct = (<ProductsActions>action).payload;
      let productList = [...state.products];
      let updatedList: Product[] = productList.map(p => p.id == updatedProduct.id ? updatedProduct : p);
      return {...state, dataState: ProductStateEnum.LOADED, products: updatedList, currentAction:<ProductsActions>action};
    case ProductActionTypes.UPDATE_PRODUCT_ERROR:
      return {...state, dataState: ProductStateEnum.ERROR, errorMessage: (<ProductsActions>action).payload, currentAction:<ProductsActions>action};

    default :
      return {...state};

  }
}
