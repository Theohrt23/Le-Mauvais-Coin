import { Injectable } from '@angular/core';
import { User } from 'src/app/shared/user';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, map } from 'rxjs/operators';
import jwt_decode from "jwt-decode";
import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {
  url: string = 'http://localhost:8080/';
  currentUserPassword: string = '';

  constructor(private http: HttpClient, public router: Router) { }

  // User edit
  editUser(user: User): Observable<any> {
    let api = `${this.url}users`;
    return this.http.put(api, user).pipe(catchError(this.handleError));
  }

  // Company edit
  editCompany(user: User): Observable<any> {
    let api = `${this.url}company`;
    return this.http.put(api, user).pipe(catchError(this.handleError));
  }

  // Check password

  checkPassword(formValue: any) {
    return this.http
      .post<any>(`${this.url}auth/verif`, formValue).pipe(catchError(this.handleError));
  }

  getUserProfile(id: any): Observable<User> {
    let api = `${this.url}users/${id}`;
    return this.http.get<User>(api).pipe(retry(1), catchError(this.handleError));
  }

  // Error
  handleError(error: HttpErrorResponse) {
    let msg = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      msg = error.error.message;
    } else {
      // server-side error
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(msg);
  }
}
