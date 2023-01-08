import { Component, OnInit } from '@angular/core';
import { offerService } from '../home/offer.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from './../../shared/auth.service';
import { Router } from '@angular/router'
import { DatePipe } from '@angular/common';
import { Offer } from '../home/offer';
import jwt_decode from "jwt-decode";
import { ChangeDetectorRef, AfterContentChecked } from '@angular/core';

@Component({
  selector: 'app-my-offers',
  templateUrl: './my-offers.component.html',
  styleUrls: ['./my-offers.component.css']
})
export class MyOffersComponent implements OnInit {

  offers: any = [];
  createOfferForm: FormGroup;
  editOfferForm: FormGroup;
  currentOffer: any = {};

  today = new Date();
  pipe = new DatePipe('en-US');
  ChangedFormat = this.pipe.transform(this.today, 'dd/MM/YYYY');

  authToken: any = localStorage.getItem('access_token');
  decoded: any = jwt_decode(this.authToken);
  errorMessage: any;
  status: any;

  constructor(public offerService: offerService, public fb: FormBuilder,
    public authService: AuthService,
    public router: Router,
    private changeDetector: ChangeDetectorRef,) {
    this.createOfferForm = this.fb.group({
      title: [''],
      description: [''],
      contract_type: [''],
      salary: [''],
      work_period: [''],
      teleworking: [''],
      creation_date: [this.ChangedFormat],
      company: [this.decoded.id],
    });
    this.editOfferForm = this.fb.group({
      id: [''],
      title: [''],
      description: [''],
      contract_type: [''],
      salary: [''],
      work_period: [''],
      teleworking: [''],
      creation_date: [this.ChangedFormat],
      company: [this.decoded.id],
    });
  }

  ngOnInit(): void {
    this.getCompanyOffers(this.decoded.id);
  }

  ngAfterContentChecked(): void {
    this.changeDetector.detectChanges();
  }

  getCompanyOffers(companyId: string) {
    return this.offerService.getCompanyOffers(companyId).subscribe((data: {}) => {
      this.offers = data;
    });
  }

  createOffer() {
    this.offerService.createOffer(this.createOfferForm.value).subscribe({
      next: (res: any) => {
        if (res) {
          window.location.reload()
          this.authService.showAlert('Annonce crée.', 'success')
        }
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de créer l\'annonce. Vérifiez vos informations !', 'danger')
      }
    });
  }

  deleteOffer(offerId: string) {
    this.offerService.deleteOffer(offerId).subscribe({
      next: (res: any) => {
          window.location.reload()
          this.authService.showAlert('Annonce supprimée.', 'success')
      },
    });
  }

  loadOffer(offerId: string){
    this.offerService.getOfferById(offerId).subscribe((data: {}) => {
      this.currentOffer = data;
    });
  }

  editOffer() {
    this.offerService.editOffer(this.editOfferForm.value).subscribe({
      next: (res: any) => {
        if (res) {
          window.location.reload()
          this.authService.showAlert('Annonce modifiée.', 'success')
        }
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de modifier l\'annonce. Vérifiez vos informations !', 'danger')
      }
    });
  }



}
