import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../enviroments/environment';

export interface Profesor {
  id_Persona: number;
  type: string;
  nombre: string;
  apellido: string;
  fechaNacimiento: string;
  email: string;
  telefono: string;
  especialidad: string;
  fechaContratacion: string;
}


@Injectable({
  providedIn: 'root'
})
export class ProfesorService {
  private apiUrl = `${environment.API_URL}/profesores`;

  constructor(private http: HttpClient) { }

  obtenerProfesores(): Observable<Profesor[]> {
    return this.http.get<Profesor[]>(this.apiUrl);
  }

  agregarProfesor(profesor: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, profesor);
  }

  ObtenerProfesorById(id: number): Observable<void> {
    return this.http.get<void>(`${this.apiUrl}/${id}`);
  }

  eliminarProfesor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  editarProfesor(profesor: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${profesor.id_Persona}`, profesor);
  }
}
