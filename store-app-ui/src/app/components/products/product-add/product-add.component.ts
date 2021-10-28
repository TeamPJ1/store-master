import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductsState, ProductStateEnum} from "../../../ngrx/products.reducer";
import {Store} from "@ngrx/store";
import {NewProductAction, SaveProductAction} from "../../../ngrx/products.actions";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {
  productFormGroup?: FormGroup | null = null;
  productState: ProductsState | null = null;
  readonly DataStateEnum = ProductStateEnum;
  submitted: Boolean = false;

  constructor(private store: Store<any>, private formBuilder: FormBuilder, private router: Router) {
  }

  ngOnInit(): void {
    this.store.dispatch(new NewProductAction({}));
    this.store.subscribe(myStore => {
      this.productState = myStore.catalogState;
      if (myStore.catalogState.dataState === ProductStateEnum.NEW) {
        this.productFormGroup = this.formBuilder.group({
          name: ["", Validators.required],
          price: [0, Validators.required],
          quantity: [0, Validators.required],
          selected: [true, Validators.required],
          available: [true, Validators.required]
        });
      }
    })
  }

  onSaveProduct() {
    this.submitted= true;
    if (this.productFormGroup?.invalid) return;
    this.store.dispatch(new SaveProductAction(this.productFormGroup?.value));
  }

  newProduct() {
    this.store.dispatch(new NewProductAction({}));
  }

  onContinue() {
    this.router.navigateByUrl("/products");
  }
}
