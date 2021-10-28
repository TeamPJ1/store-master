export interface Product {
  id:number;
  name:string;
  price:number;
  quantity:number;
  selected:boolean;
}

export interface GetProductResponse {
  _embedded: {
    products: Product[];
    _links: {self: {href: string}};
  };
}
