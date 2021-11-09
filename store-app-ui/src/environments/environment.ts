// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

import {HttpHeaders} from "@angular/common/http";

export const environment = {
  production: false,
  host: "Http://localhost:8700",
  productBackendAPI: "inventory-service",
  // host: "Http://localhost:3000", //host nodejs json-server
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
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
