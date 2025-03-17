import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../enviroments/environment';
import { TokenService } from './token.service';

export interface Student {
  id_Persona: number;
  type: string;
  nombre: string;
  apellido: string;
  fechaNacimiento: string;
  email: string;
  telefono: string;
  numeroMatricula: string;
  grado: number;
}

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiUrl = `${environment.API_URL}/api/v1/estudiantes`;

  constructor(private http: HttpClient, private tokenService: TokenService) { }

  private getHeaders(): HttpHeaders {
    const token = this.tokenService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  obtenerStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  agregarStudent(student: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, student, { headers: this.getHeaders() });
  }

  ObtenerStudentById(id: number): Observable<void> {
    return this.http.get<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  eliminarStudent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  editarStudent(student: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${student.id_Persona}`, student, { headers: this.getHeaders() });
  }
}
