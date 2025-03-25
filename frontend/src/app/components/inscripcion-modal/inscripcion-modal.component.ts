import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';

import { CourseService } from '../../services/course.service';

import { StudentService } from '../../services/student.service';
import { InscriptionService } from '../../services/inscription.service';

@Component({
  selector: 'app-inscripcion-modal',
  templateUrl: './inscripcion-modal.component.html',
  styleUrls: ['./inscripcion-modal.component.css'],
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    MatDialogModule,
    MatLabel,
    MatFormFieldModule]
})
export class InscripcionModalComponent implements OnInit {
  titulo: string = 'Nueva Inscripción';
  inscripcion = {
    id_inscripcion: 0,
    id_estudiante: 0,
    id_curso: 0,
    fecha_inscripcion: ''
  };
  estudiantes: any[] = []; // List of students
  cursos: any[] = []; // List of courses
  today: string;

  constructor(
    private dialogRef: MatDialogRef<InscripcionModalComponent>,
    private estudianteService: StudentService,
    private cursoService: CourseService,
    private inscripcionService: InscriptionService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    const now = new Date();
    this.today = now.toISOString().split('T')[0]; // Format as YYYY-MM-DD
  }

  ngOnInit(): void {
    this.cargarEstudiantes();
    this.cargarCursos();
  }

  cerrarModal(): void {
    this.dialogRef.close();
  }

  cargarEstudiantes(): void {
    this.estudianteService.obtenerStudents().subscribe({
      next: (response) => {
        this.estudiantes = response;
      },
      error: (error) => {
        console.error('Error al obtener los estudiantes:', error);
      }
    });
  }

  cargarCursos(): void {
    this.cursoService.obtenerCursos().subscribe({
      next: (response) => {
        this.cursos = response;
      },
      error: (error) => {
        console.error('Error al obtener los cursos:', error);
      }
    });
  }

  guardarInscripcion(): void {
    console.log('Datos de la inscripción antes de enviar:', this.inscripcion);
    this.inscripcionService.agregarInscripcion(this.inscripcion).subscribe({
      next: (response) => {
        console.log('Inscripción guardada con éxito:', response);
        this.snackBar.open('Inscripción guardada con éxito', 'Cerrar', {
          duration: 3000
        }).afterDismissed().subscribe(() => {
          this.dialogRef.close(response);
        });
      },
      error: (error) => {
        console.error('Error al guardar la inscripción:', error);
      }
    });
  }
}
