import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {ProductsState,ProductStateEnum} from "../../../ngrx/products.reducer";
@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {

  @Input() productsStateInput: ProductsState| null = null;

  constructor() { }

  ngOnInit(): void {
  }

}
