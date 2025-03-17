import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../enviroments/environment';
import { TokenService } from './token.service';

export interface Inscription {
  id_inscripcion: number;
  id_estudiante: number;
  id_curso: number;
  fecha_inscripcion: string;
}

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {
  private apiUrl = `${environment.API_URL}/api/v1/inscripciones`;

  constructor(private http: HttpClient, private tokenService: TokenService) { }

  private getHeaders(): HttpHeaders {
    const token = this.tokenService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  obtenerInscripciones(): Observable<Inscription[]> {
    return this.http.get<Inscription[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  agregarInscripcion(inscripcion: Inscription): Observable<Inscription> {
    return this.http.post<Inscription>(this.apiUrl, inscripcion, { headers: this.getHeaders() });
  }

  obtenerInscripcionPorId(id: number): Observable<Inscription> {
    return this.http.get<Inscription>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  eliminarInscripcion(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  editarInscripcion(inscripcion: Inscription): Observable<Inscription> {
    return this.http.put<Inscription>(`${this.apiUrl}/${inscripcion.id_inscripcion}`, inscripcion, { headers: this.getHeaders() });
  }
}
