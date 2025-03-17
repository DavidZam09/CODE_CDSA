import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/environment';
import { Observable } from 'rxjs';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private baseUrl = `${environment.API_URL}/auth`;

  constructor(private http: HttpClient, private tokenService: TokenService) { }

  register(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user);
  }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, credentials);
  }

  setToken(token: string): void {
    this.tokenService.setToken(token);
  }

  getToken(): string | null {
    return this.tokenService.getToken();
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}
