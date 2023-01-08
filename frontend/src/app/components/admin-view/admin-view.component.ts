import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { AdminService } from './admin.service';
import { offerService } from '../home/offer.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { AuthService } from 'src/app/shared/auth.service';
import jwt_decode from "jwt-decode";

@Component({
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css']
})
export class AdminViewComponent implements OnInit {

  currentOffer: any = {};
  currentUser: any = {};
  today = new Date();
  pipe = new DatePipe('en-US');
  users: any = [];
  patchOfferForm: FormGroup;
  patchUserForm: FormGroup;
  offers: any = [];
  applications: any = [];
  status: string = '';
  errorMessage: any = '';
  ChangedFormat = this.pipe.transform(this.today, 'dd/MM/YYYY');
  authToken: any = localStorage.getItem('access_token');
  decoded: any = jwt_decode(this.authToken);

  constructor(public restApi: AdminService, public offerApi: offerService,  public fb: FormBuilder, private changeDetector: ChangeDetectorRef, public authService: AuthService) {

    this.patchOfferForm = this.fb.group({
      id: [''],
      title: [''],
      description: [''],
      contract_type: [''],
      salary: [''],
      work_period: [''],
      teleworking: [''],
      creation_date: [this.ChangedFormat],
      company: [''],
    });
    this.patchUserForm = this.fb.group({                   
      id: [''],
      offers: [[]],
      mail: [''],
      name: [''],
      password: [''],
      surname: [''],
      username: [''],
      address: [''],
      city: [''],
      zip_code: [''],
    });
   }

  ngOnInit(): void {
    this.loadUsers();
    this.loadOffers();
    this.loadApplications();
  }

  loadUsers() {
    return this.restApi.getUsers().subscribe((data: {}) => {
      this.users = data;
    });
  }

  loadOffers() {
    return this.offerApi.getOffers().subscribe((data: {}) => {
      this.offers = data;
    });
  }

  loadApplications() {
    return this.restApi.getApplications().subscribe((data: {}) => {
      this.applications = data;
    });
  }

  loadCurrentOffer(offerId: string){
    this.offerApi.getOfferById(offerId).subscribe((data: {}) => {
      this.currentOffer = data;
    });
  }

  loadCurrentUser(userId: string){
    this.restApi.isCompany(userId).subscribe((res) => {
      if (res === true) {
      this.restApi.getCompanyById(userId).subscribe((data: {}) => {
        this.currentUser = data;
      });
      }else {
        this.restApi.getUserById(userId).subscribe((data: {}) => {
          this.currentUser = data;
        });
      }
    });
    }

  public patchOffers(){
   this.offerApi.editOffer(this.patchOfferForm.value).subscribe({
    next: (res: any) => {
      window.location.reload()
      this.authService.showAlert('Votre changement a bien été effectué !', 'success')
    },
    error: (err: any) => {
      this.authService.showAlert('Impossible de modifier l\'offre ! Vérifiez vos informations !', 'danger')
    }
  });
  }

  public patchUsers(){
    this.restApi.isCompany(this.patchUserForm.value.id).subscribe((res) => {
      if (res === true) {
        this.restApi.editCompany(this.patchUserForm.value).subscribe({
          next: (res: any) => {
            window.location.reload()
            this.authService.showAlert('Votre changement a bien été effectué !', 'success')
          },
          error: (err: any) => {
            this.authService.showAlert('Impossible de modifier l\'utilisateur ! Vérifiez vos informations !', 'danger')
          }
        });
      }else {
        this.restApi.editUser(this.patchUserForm.value).subscribe({
          next: (res: any) => {
            window.location.reload()
            this.authService.showAlert('Votre changement a bien été effectué !', 'success')
          },
          error: (err: any) => {
            this.authService.showAlert('Impossible de modifier l\'utilisateur ! Vérifiez vos informations !', 'danger')
          }
        });
        }
    });
    }
  

  public deleteUsers(id: number, type: string){
    if (type == "company"){
      this.restApi.deleteCompanys(id);
    }else {
      this.restApi.deleteUsers(id);
    }
    window.location.reload();
    this.showUsers();
  }

  public deleteApplications(id: number){
    this.restApi.deleteApplications(id).subscribe({
      next: (res: any) => {
        window.location.reload()
        this.authService.showAlert('Application supprimée !', 'success')
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de supprimer l\'application !', 'danger')
      }
    });
    this.showApplications();
  }

  public deleteOffers(id: string){
    this.offerApi.deleteOffer(id).subscribe({
      next: (res: any) => {
        window.location.reload()
        this.authService.showAlert('Annonce supprimée!', 'success')
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de supprimer l\'annonce !', 'danger')
      }
    });
    this.showOffers();
  }

  public showUsers() {
    document.getElementById("offers")?.classList.add("d-none");
    document.getElementById("empty-offers")?.classList.add("d-none");
    document.getElementById("applications")?.classList.add("d-none");
    document.getElementById("empty-applications")?.classList.add("d-none");
    document.getElementById("users")?.classList.remove("d-none");
    document.getElementById("empty-users")?.classList.remove("d-none");
  }

  public showOffers() {
    document.getElementById("users")?.classList.add("d-none");
    document.getElementById("empty-users")?.classList.add("d-none");
    document.getElementById("applications")?.classList.add("d-none");
    document.getElementById("empty-applications")?.classList.add("d-none");
    document.getElementById("offers")?.classList.remove("d-none");
    document.getElementById("empty-offers")?.classList.remove("d-none");
  }

  public showApplications() {
    document.getElementById("offers")?.classList.add("d-none");
    document.getElementById("empty-offers")?.classList.add("d-none");
    document.getElementById("users")?.classList.add("d-none");
    document.getElementById("empty-users")?.classList.add("d-none");
    document.getElementById("applications")?.classList.remove("d-none");
    document.getElementById("empty-applications")?.classList.remove("d-none");
  }





}
