import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../enviroments/environment';
import { TokenService } from './token.service';

export interface Course {
  id_curso: number;
  nombre: string;
  descripcion: string;
  creditos: number;
  idProfesor: number;
}

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private apiUrl = `${environment.API_URL}/api/v1/cursos`;

  constructor(private http: HttpClient, private tokenService: TokenService) { }

  private getHeaders(): HttpHeaders {
    const token = this.tokenService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  obtenerCursos(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  agregarCurso(course: Course): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course, { headers: this.getHeaders() });
  }

  obtenerCursoPorId(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  eliminarCurso(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  editarCurso(course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${course.id_curso}`, course, { headers: this.getHeaders() });
  }
}
