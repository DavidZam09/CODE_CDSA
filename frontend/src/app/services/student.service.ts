import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../enviroments/environment';

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

  constructor(private http: HttpClient) { }

  obtenerStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  agregarStudent(student: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, student);
  }

  ObtenerStudentById(id: number): Observable<void> {
    return this.http.get<void>(`${this.apiUrl}/${id}`);
  }

  eliminarStudent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  editarStudent(student: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${student.id_Persona}`, student);
  }
}
