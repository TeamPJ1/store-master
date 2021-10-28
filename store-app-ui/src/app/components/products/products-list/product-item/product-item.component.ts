import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../../../../model/product.model";
import {ProductsState} from "../../../../ngrx/products.reducer";
import {Store} from "@ngrx/store";
import {DeleteProductAction, SelectProductAction} from "../../../../ngrx/products.actions";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {
  @Input() product: Product | null = null;

  constructor(private store: Store<any>, private router: Router) {
  }

  ngOnInit(): void {
  }

  onSelectProduct(p: Product) {
    this.store.dispatch(new SelectProductAction(p));
  }

  onDeleteProduct(p: Product) {
    this.store.dispatch(new DeleteProductAction(p));
  }

  onEditProduct(p: Product) {
    this.router.navigateByUrl("/editProduct/"+p.id);
  }

}
