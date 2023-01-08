import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from './../../shared/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup;
  companySignupForm: FormGroup;
  constructor(
    public fb: FormBuilder,
    public authService: AuthService,
    public router: Router
  ) {
    this.signupForm = this.fb.group({
      name: [''],
      surname: [''],
      username: [''],
      mail: [''],
      password: [''],
      offers: [[]],
    });
    this.companySignupForm = this.fb.group({
      name: [''],
      surname: [''],
      username: [''],
      mail: [''],
      address: [''],
      city: [''],
      zip_code: [''],
      password: [''],
      offers: [[]],
    });
  }
  ngOnInit() { }
  registerUser() {
    this.authService.userSignUp(this.signupForm.value).subscribe({
      next: (res: any) => {
        if (res) {
          this.signupForm.reset();
          this.router.navigate(['log-in']);
        }
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de créer le compte. Vérifiez vos informations !', 'danger')
      }
    });
  }
  registerCompany() {
    this.authService.companySignUp(this.companySignupForm.value).subscribe({
      next: (res: any) => {
        if (res) {
          this.companySignupForm.reset();
          this.router.navigate(['log-in']);
        }
      },
      error: (err: any) => {
        this.authService.showAlert('Impossible de créer le compte. Vérifiez vos informations !', 'danger')
      }
    });
  }

  showUser() {
    document.querySelector('#user_form')?.classList.remove('d-none')
    document.querySelector('#company_form')?.classList.add('d-none')
  }

  showCompany() {
    document.querySelector('#company_form')?.classList.remove('d-none')
    document.querySelector('#user_form')?.classList.add('d-none')
  }
}