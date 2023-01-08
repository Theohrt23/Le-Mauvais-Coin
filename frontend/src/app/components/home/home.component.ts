import { Component, OnInit } from '@angular/core';
import { offerService } from './offer.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from './../../shared/auth.service';
import { Router } from '@angular/router'
import jwt_decode from "jwt-decode";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  offers: any = [];
  applyForm: FormGroup;
  connectedApplyForm: FormGroup;
  currentUser: any = {};
  currentOffer: any = {};

  constructor(public offerService: offerService, public fb: FormBuilder,
    public authService: AuthService,
    public router: Router) {
    this.applyForm = this.fb.group({
      name: [''],
      surname: [''],
      mail: [''],
      body: [''],
      subject: [''],
      offer_id: [''],
    });

    this.connectedApplyForm = this.fb.group({
      user_id: [''],
      body: [''],
      subject: [''],
      offer_id: [''],
    });
  }

  ngOnInit(): void {
    this.loadOffers();
  }

  loadOffers() {
    return this.offerService.getOffers().subscribe((data: {}) => {
      this.offers = data;
    });
  }

  selectedOffer?: String;
  onSelect(offerId: string, offer_teleworking: boolean): void {
    document.querySelector('#offer' + offerId)?.classList.remove('d-none')
    document.querySelector('#btn_offer' + offerId)?.classList.remove('d-none')
    document.querySelector('#ap_btn_offer' + offerId)?.classList.remove('d-none')
    document.querySelector('#lm_btn_offer' + offerId)?.classList.add('d-none')
    if (offer_teleworking === true) {
      document.querySelector('#twy_offer' + offerId)?.classList.remove('d-none')
    } else {
      document.querySelector('#twn_offer' + offerId)?.classList.remove('d-none')
    }
  }

  unselect(offerId: string): void {
    document.querySelector('#offer' + offerId)?.classList.add('d-none')
    document.querySelector('#btn_offer' + offerId)?.classList.add('d-none')
    document.querySelector('#ap_btn_offer' + offerId)?.classList.add('d-none')
    document.querySelector('#lm_btn_offer' + offerId)?.classList.remove('d-none')
  }

  apply() {
    this.offerService.applyToOffer(this.applyForm.value).subscribe({
      next: (res: any) => {
        window.location.reload()
        this.authService.showAlert('Votre demande a bien été envoyé !', 'success')
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de postuler. Vérifiez vos informations !', 'danger')
      }
    });
  }

  userApply() {
    this.offerService.applyToOffer(this.connectedApplyForm.value).subscribe({
      next: (res: any) => {
        window.location.reload()
        this.authService.showAlert('Votre demande a bien été envoyé !', 'success')
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de postuler. Vérifiez vos informations !', 'danger')
      }
    });
  }

  loadUserProfile() {
    if (!this.authService.isLoggedIn) {
      return this.currentUser;
    }
    let authToken: any = localStorage.getItem('access_token');
    let decoded: any = jwt_decode(authToken);
    return this.authService.getUserProfile(decoded.id).subscribe((data: {}) => {
      this.currentUser = data;
    })
  }

  loadOffer(offerId: string) {
    this.offerService.getOfferById(offerId).subscribe((data: {}) => {
      this.currentOffer = data;
    });
  }
}
