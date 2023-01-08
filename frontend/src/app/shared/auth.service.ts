import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, map } from 'rxjs/operators';
import jwt_decode from "jwt-decode";
import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';
import { Router } from '@angular/router';
import { RoleCheckService } from './role-check.service';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  url: string = 'http://localhost:8080/';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  currentUser = {};
  constructor(private http: HttpClient, public router: Router, public roleCheckService: RoleCheckService) { }
  // User Sign-up
  userSignUp(user: User): Observable<any> {
    let api = `${this.url}users`;
    return this.http.post(api, user).pipe(catchError(this.handleError));
  }
  // Company Sign-up
  companySignUp(user: User): Observable<any> {
    let api = `${this.url}company`;
    return this.http.post(api, user).pipe(catchError(this.handleError));
  }
  // User Sign-in
  signIn(user: User) {
    return this.http
      .post<any>(`${this.url}auth/signin`, user)
      .subscribe((res: any) => {
        localStorage.setItem('access_token', res.token);
        var decoded: any = jwt_decode(res.token);
        if (this.roleCheckService.isUser) {
          this.getUserProfile(decoded.id).subscribe((res) => {
            this.currentUser = res;
            this.router.navigate(['home']);
          });
        }

        if (this.roleCheckService.isCompany) {
          this.getCompanyProfile(decoded.id).subscribe((res) => {
            this.currentUser = res;
            this.router.navigate(['my-offers']);
          });
        }

        if (this.roleCheckService.isAdmin) {
          this.router.navigate(['admin']);
        }
      },
        (err) => {
          this.showAlert('Erreur de connexion. Vérifiez votre Nom d\'utilisateur/Mot de passe !', 'danger')
        });
  }
  getToken() {
    return localStorage.getItem('access_token');
  }
  get isLoggedIn(): boolean {
    let authToken = localStorage.getItem('access_token');
    return authToken !== null ? true : false;
  }
  doLogout() {
    let removeToken = localStorage.removeItem('access_token');
    if (removeToken == null) {
      this.router.navigate(['log-in']);
    }
  }
  // User profile
  getUserProfile(id: any): Observable<User> {
    let api = `${this.url}users/${id}`;
    return this.http.get<User>(api).pipe(retry(1), catchError(this.handleError));
  }
  // User profile
  getCompanyProfile(id: any): Observable<User> {
    let api = `${this.url}company/${id}`;
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
    document.querySelector('#user_form')?.classList.remove('d-none')
    return throwError(msg);
  }

  showAlert(message: String, type: String) {
    let alertPlaceholder = document.getElementById('alert_message')
    if (alertPlaceholder) {
      const wrapper = document.createElement('div')
      wrapper.innerHTML = [
        `<div class="alert alert-${type} alert-dismissible" role="alert">`,
        `   <div>${message}</div>`,
        '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '</div>'
      ].join('')
      alertPlaceholder.append(wrapper)
    }
  }
}