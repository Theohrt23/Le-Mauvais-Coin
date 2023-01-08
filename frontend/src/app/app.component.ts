import { Component } from '@angular/core';
import { AuthService } from './shared/auth.service';
import { RoleCheckService } from './shared/role-check.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(public authService: AuthService, public roleCheckService: RoleCheckService) { }
  logout() {
    this.authService.doLogout()
  }
}