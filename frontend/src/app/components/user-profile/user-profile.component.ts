import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from './../../shared/auth.service';
import { User } from 'src/app/shared/user';
import jwt_decode from "jwt-decode";
import { Router } from '@angular/router';
import { UserProfileService } from './user-profile.service';
import { RoleCheckService } from '../../shared/role-check.service';
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
})
export class UserProfileComponent implements OnInit {
  editUserForm: FormGroup;
  editUserCompanyForm: FormGroup;
  currentUser: any = {};
  currentUserPassword = '';
  object: any = {};
  authToken: any = localStorage.getItem('access_token');
  decoded: any = jwt_decode(this.authToken);
  constructor(
    public authService: AuthService,
    private actRoute: ActivatedRoute,
    public fb: FormBuilder,
    public router: Router,
    public userProfileService: UserProfileService,
    public roleCheckService: RoleCheckService
  ) {
    this.editUserForm = this.fb.group({
      id: [this.decoded.id],
      name: [''],
      surname: [''],
      username: [''],
      mail: [''],
      password: [''],
    });
    this.editUserCompanyForm = this.fb.group({
      id: [this.decoded.id],
      name: [''],
      surname: [''],
      username: [''],
      mail: [''],
      address: [''],
      city: [''],
      zip_code: [''],
      offers: [[]],
      password: [''],
    });
  }
  ngOnInit(): void {
    if (this.roleCheckService.isCompany) {
      this.loadCompanyProfile();
    }
    if (this.roleCheckService.isUser) {
      this.loadUserProfile();
    }
  }

  loadUserProfile() {
    return this.authService.getUserProfile(this.decoded.id).subscribe((data: {}) => {
      this.currentUser = data;
    })

  }

  loadCompanyProfile() {
    return this.authService.getCompanyProfile(this.decoded.id).subscribe((data: {}) => {
      this.currentUser = data;
    })

  }

  editUser() {
    this.userProfileService.editUser(this.editUserForm.value).subscribe({
      next: (res: any) => {
        window.location.reload()
        this.authService.showAlert('Votre changement a bien été effectué !', 'success')
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de modifier l\'utilisateur ! Vérifiez vos informations !', 'danger')
      }
    });
  }

  editCompany() {
    this.userProfileService.editCompany(this.editUserCompanyForm.value).subscribe({
      next: (res: any) => {
        window.location.reload()
        this.authService.showAlert('Votre changement a bien été effectué !', 'success')
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de modifier l\'utilisateur ! Vérifiez vos informations !', 'danger')
      }
    });
  }

  checkUserPassword() {
    this.object.password = this.editUserForm.value.password;
    this.object.user_id = this.editUserForm.value.id;
    this.userProfileService.checkPassword(this.object).subscribe((res) => {
      if (res === true) {
        this.currentUserPassword = this.editUserForm.value.password;
        this.editUser();
      } else {
        this.authService.showAlert('Mot de passe incorrect !', 'danger')
      }
    })
    return this.currentUserPassword;
  }

  checkCompanyPassword() {
    this.object.password = this.editUserCompanyForm.value.password;
    this.object.user_id = this.editUserCompanyForm.value.id;
    this.userProfileService.checkPassword(this.object).subscribe((res) => {
      if (res === true) {
        this.currentUserPassword = this.editUserCompanyForm.value.password;
        this.editCompany();
      } else {
        this.authService.showAlert('Mot de passe incorrect !', 'danger')
      }
    })
    return this.currentUserPassword;
  }
}