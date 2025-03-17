import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { CourseService } from '../../services/course.service';
import { InscriptionService } from '../../services/inscription.service';
import { StudentService } from '../../services/student.service';
import { MatDialog } from '@angular/material/dialog';
import { InscripcionModalComponent } from '../inscripcion-modal/inscripcion-modal.component';

@Component({
  selector: 'app-list-inscripciones',
  templateUrl: './list-inscripciones.component.html',
  styleUrls: ['./list-inscripciones.component.css'],
  imports: [CommonModule, NgFor]
})
export class ListInscripcionesComponent implements OnInit {
  inscripciones: any[] = []; // List of inscriptions
  estudiantes: Map<number, string> = new Map(); // Map to store student names by ID
  cursos: Map<number, string> = new Map(); // Map to store course names by ID

  constructor(
    private inscripcionService: InscriptionService,
    private estudianteService: StudentService,
    private cursoService: CourseService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.obtenerEstudiantes();
    this.obtenerCursos();
    this.obtenerInscripciones();
  }

  obtenerEstudiantes(): void {
    this.estudianteService.obtenerStudents().subscribe({
      next: (response) => {
        response.forEach((estudiante: any) => {
          this.estudiantes.set(estudiante.id_Persona, `${estudiante.nombre} ${estudiante.apellido}`);
        });
      },
      error: (error) => {
        console.error('Error al obtener los estudiantes:', error);
      }
    });
  }

  agregarInscripcion(): void {
    this.dialog.open(InscripcionModalComponent, {
      width: '500px',
      data: { inscripcion: {} }
    });
  }

  eliminarInscripcion(id_inscripcion: number): void {
    this.inscripcionService.eliminarInscripcion(id_inscripcion).subscribe({
      next: () => {
        this.obtenerInscripciones();
      },
      error: (error) => {
        console.error('Error al eliminar la inscripción:', error);
      }
    });
  }

  editarInscripcion(inscripcion: any): void {
    this.dialog.open(InscripcionModalComponent, {
      width: '500px',
      data: { inscripcion }
    });
  }
  obtenerCursos(): void {
    this.cursoService.obtenerCursos().subscribe({
      next: (response) => {
        response.forEach((curso: any) => {
          this.cursos.set(curso.id_curso, curso.nombre);
        });
      },
      error: (error) => {
        console.error('Error al obtener los cursos:', error);
      }
    });
  }

  obtenerInscripciones(): void {
    this.inscripcionService.obtenerInscripciones().subscribe({
      next: (response) => {
        this.inscripciones = response;
      },
      error: (error) => {
        console.error('Error al obtener las inscripciones:', error);
      }
    });
  }

  getNombreEstudiante(id_estudiante: number): string {
    return this.estudiantes.get(id_estudiante) || 'Desconocido';
  }

  getNombreCurso(id_curso: number): string {
    return this.cursos.get(id_curso) || 'Desconocido';
  }
}
