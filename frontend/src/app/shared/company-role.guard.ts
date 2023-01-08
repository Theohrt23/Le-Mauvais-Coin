import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RoleCheckService } from './role-check.service';

@Injectable({
  providedIn: 'root'
})
export class CompanyRoleGuard implements CanActivate {
  constructor(
    public roleCheckService: RoleCheckService,
    public router: Router
  ) { }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.roleCheckService.isCompany !== true) {
      window.alert("Access not allowed!");
      this.router.navigate(['home'])
    }
    return true;
  }
}
