import { Component } from '@angular/core';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../../shared/user';
import { Application } from './application'
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { AuthService } from 'src/app/shared/auth.service';
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  apiURL = 'http://localhost:8080';

  constructor(private http: HttpClient, public authService: AuthService) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  getUsers(): Observable<User> {
    return this.http
      .get<User>(this.apiURL + '/users')
      .pipe(retry(1), catchError(this.handleError));
  }

  getApplications(): Observable<Application> {
    return this.http
      .get<Application>(this.apiURL + '/application')
      .pipe(retry(1), catchError(this.handleError));
  }

  getUserById(id: string): Observable<User> {
    let api = `${this.apiURL}/users/${id}`;
    return this.http
      .get<User>(api)
      .pipe(retry(1), catchError(this.handleError));
  }

  getCompanyById(id: string): Observable<User> {
    let api = `${this.apiURL}/company/${id}`;
    return this.http
      .get<User>(api)
      .pipe(retry(1), catchError(this.handleError));
  }

  deleteApplications(id: number) {
    return this.http.delete(this.apiURL + '/application/' + id);
  }

  editUser(user: User): Observable<any> {
    let api = `${this.apiURL}/users`;
    return this.http.put(api, user).pipe(catchError(this.handleError));
  }

  editCompany(user: User): Observable<any> {
    let api = `${this.apiURL}/company`;
    return this.http.put(api, user).pipe(catchError(this.handleError));
  }

  isCompany(id: string) {
    return this.http
      .get(this.apiURL + '/users/isCompany/' + id).pipe(catchError(this.handleError));
  }
  isAdmin(id: string) {
    return this.http
      .get(this.apiURL + '/users/isAdmin/' + id).pipe(catchError(this.handleError));
  }

  deleteUsers(id: number): void {
    this.http.delete(this.apiURL + '/users/' + id).subscribe({
      next: (res: any) => {
        window.location.reload()
        this.authService.showAlert('Votre utilisateur a été supprimée !', 'success')
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de surpprimer l\'utilisateur !', 'danger')
      }
    });
  }

  deleteCompanys(id: number): void {
    this.http.delete(this.apiURL + '/company/' + id).subscribe({
      next: (res: any) => {
        window.location.reload()
        this.authService.showAlert('Votre changement a bien été effectué !', 'success')
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de modifier l\'utilisateur ! Vérifiez vos informations !', 'danger')
      }
    });
  }

  // Error handling
  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(() => {
      return errorMessage;
    });
  }
}
