import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class RoleCheckService {

  constructor() { }

  get isUser(): boolean {
    let authToken: any = localStorage.getItem('access_token');
    if (authToken) {
      let decoded: any = jwt_decode(authToken);
      if (decoded.role == 'ROLE_USER') {
        return true;
      }
    }
    return false;
  }
  
  get isCompany(): boolean {
    let authToken: any = localStorage.getItem('access_token');
    if (authToken) {
      let decoded: any = jwt_decode(authToken);
      if (decoded.role == 'ROLE_COMPANY') {
        return true;
      }
    }
    return false;
  }

  get isAdmin(): boolean {
    let authToken: any = localStorage.getItem('access_token');
    if (authToken) {
      let decoded: any = jwt_decode(authToken);
      if (decoded.role == 'ROLE_ADMIN') {
        return true;
      }
    }
    return false;
  }
}
