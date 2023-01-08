import { Component } from '@angular/core';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Offer } from './offer';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
@Injectable({
  providedIn: 'root',
})

export class offerService {

  url = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  // Get all offers

  getOffers(): Observable<Offer> {
    return this.http
      .get<Offer>(this.url + '/offers')
      .pipe(retry(1), catchError(this.handleError));
  }

  getOfferById(id: string): Observable<Offer> {
    let api = `${this.url}/offers/${id}`;
    return this.http
      .get<Offer>(api)
      .pipe(retry(1), catchError(this.handleError));
  }

  // Get offers by company id

  getCompanyOffers(id: string): Observable<Offer> {
    let api = `${this.url}/offers/getAllOffer/${id}`;
    return this.http
      .get<Offer>(api)
      .pipe(retry(1), catchError(this.handleError));
  }

  // Create Offer 

  createOffer(offer: Offer): Observable<any> {
    let api = `${this.url}/offers`;
    return this.http.post(api, offer).pipe(catchError(this.handleError));
  }

  // edit Offer 

  editOffer(offer: Offer): Observable<any> {
    let api = `${this.url}/offers`;
    return this.http.put(api, offer).pipe(catchError(this.handleError));
  }

  // Delete Offer

  deleteOffer(id: string): Observable<any> {
    let api = `${this.url}/offers/${id}`;
    return this.http.delete(api).pipe(catchError(this.handleError));
  }

  // Apply to Offer

  applyToOffer(offer: Offer): Observable<any> {
    let api = `${this.url}/application`;
    return this.http.post(api, offer).pipe(catchError(this.handleError));
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
