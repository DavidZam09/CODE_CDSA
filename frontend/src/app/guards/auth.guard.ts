import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { TokenService } from '../services/token.service';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private tokenService: TokenService, private router: Router) { }

  canActivate(): boolean {
    const token = this.tokenService.getToken();
    if (token && !this.isTokenExpired(token)) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

  private isTokenExpired(token: string): boolean {
    try {
      const decoded: any = jwtDecode(token); // Corrected function name
      const currentTime = Math.floor(Date.now() / 1000);
      return decoded.exp < currentTime;
    } catch (error) {
      return true; // Consider token expired if it cannot be decoded
    }
  }
}
