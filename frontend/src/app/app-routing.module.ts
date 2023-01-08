import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomeComponent } from './components/home/home.component';
import { AppComponent } from './app.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { MyOffersComponent } from './components/my-offers/my-offers.component';
import { AuthGuard } from "./shared/auth.guard";
import { CommonModule } from '@angular/common';
import { AdminViewComponent } from './components/admin-view/admin-view.component';
import { ContactComponent } from './components/contact/contact.component'
import { RoleGuard } from './shared/role.guard';
import { CompanyRoleGuard } from './shared/company-role.guard';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'app', component: AppComponent },
  { path: 'log-in', component: SigninComponent },
  { path: 'sign-up', component: SignupComponent },
  { path: 'user-profile', component: UserProfileComponent, canActivate: [AuthGuard] },
  { path: 'my-offers', component: MyOffersComponent, canActivate: [CompanyRoleGuard] },
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminViewComponent, canActivate: [RoleGuard] },
  { path: 'contact', component: ContactComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes), CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
