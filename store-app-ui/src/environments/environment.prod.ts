export const environment = {
  production: true,
  host: "Http://localhost:8700",
  productBackendAPI: "inventory-service",
  unreachableHost: "Http://localhost:8000"
};

export const httpOptions = {
  headers: {
    'Access-Control-Allow-Origin': 'http://localhost:8700',
    'Access-Control-Allow-Headers': 'Content-Type',
    'content-type': 'application/json',
    'Host': 'localhost:8700',
    'Origin': 'http://localhost:8700',
    'Referer': 'http://localhost:8700',
    'accept': 'application/json',
    'Access-Control-Allow-Methods': 'PUT',
    'Sec-Fetch-Mode': 'no-cors'
  }
};
