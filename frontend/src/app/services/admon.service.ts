import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../enviroments/environment';
import { TokenService } from './token.service';

export interface Administrativo {
  id_Persona: number;
  type: string;
  nombre: string,
  apellido: string,
  fechaNacimiento: string,
  email: string,
  telefono: string,
  cargo: string,
  departamento: string
}

@Injectable({
  providedIn: 'root'
})
export class AdmonService {
  private apiUrl = `${environment.API_URL}/api/v1/administrativos`;

  constructor(private http: HttpClient, private tokenService: TokenService) { }

  private getHeaders(): HttpHeaders {
    const token = this.tokenService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  obtenerAdministrativos(): Observable<Administrativo[]> {
    return this.http.get<Administrativo[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  agregarAdministrativo(administrativo: Administrativo): Observable<Administrativo> {
    return this.http.post<Administrativo>(this.apiUrl, administrativo, { headers: this.getHeaders() });
  }

  obtenerAdministrativoPorId(id: number): Observable<Administrativo> {
    return this.http.get<Administrativo>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  eliminarAdministrativo(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  editarAdministrativo(administrativo: Administrativo): Observable<Administrativo> {
    return this.http.put<Administrativo>(`${this.apiUrl}/${administrativo.id_Persona}`, administrativo, { headers: this.getHeaders() });
  }
}
