import {Component, OnInit} from '@angular/core';
import {EditProductAction, NewProductAction, UpdateProductAction} from "../../../ngrx/products.actions";
import {ProductsState, ProductStateEnum} from "../../../ngrx/products.reducer";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Store} from "@ngrx/store";
import {Product} from "../../../model/product.model";

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  productID: number;
  productState: ProductsState | null = null;
  productFormGroup: FormGroup | null = null;
  formBuilt: boolean = false;
  readonly DataStateEnum = ProductStateEnum;
  submitted: Boolean = false;

  constructor(private activatedRoute: ActivatedRoute, private store: Store<any>, private formBuilder: FormBuilder, private router: Router) {
    this.productID = activatedRoute.snapshot.params.id;
  }

  ngOnInit(): void {
    this.store.dispatch(new EditProductAction(this.productID));

    this.store.subscribe(myStore => {
      this.productState = myStore.catalogState;
      if (myStore.catalogState.dataState === ProductStateEnum.EDIT) {
        // create form
        this.productFormGroup = this.formBuilder.group({});
        let data = this.productState?.currentProduct;
        for (let f in data) {
          //@ts-ignore
          this.productFormGroup?.addControl(f, new FormControl(data[f], Validators.required));
        }
        this.formBuilt = true;
        // create form with classic manner
        // this.productFormGroup = this.formBuilder.group({
        //   id: [this.productState?.currentProduct?.id, Validators.required],
        //   name: [this.productState?.currentProduct?.name, Validators.required],
        //   price: [this.productState?.currentProduct?.price, Validators.required],
        //   quantity: [this.productState?.currentProduct?.quantity, Validators.required],
        //   selected: [this.productState?.currentProduct?.selected],
        //   available: [this.productState?.currentProduct?.available]
        // });
      }
    })
  }

  okUpdated() {
    this.router.navigateByUrl("/products");
  }

  onUpdateProduct() {
    this.submitted = true;
    if (!this.productFormGroup?.value) return;
    this.store.dispatch(new UpdateProductAction(this.productFormGroup?.value));

  }
}
