import { Component, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { Router } from '@angular/router';
import { CourseService } from '../../services/course.service';
import { ProfesorService, Profesor } from '../../services/profesor.service';

@Component({
  selector: 'app-curso-modal',
  templateUrl: './curso-modal.component.html',
  styleUrls: ['./curso-modal.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    MatSelectModule,
    MatOptionModule
  ]
})
export class CursoModalComponent {
  titulo: string;
  curso = {
    id_curso: 0,
    nombre: '',
    descripcion: '',
    creditos: 0,
    idProfesor: 0
  };
  profesores: Profesor[] = []; // List of professors

  constructor(
    private dialogRef: MatDialogRef<CursoModalComponent>,
    private cursoService: CourseService,
    private profesorService: ProfesorService,
    private snackBar: MatSnackBar,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.curso = data.curso ? { ...data.curso } : {};
    this.titulo = data.curso ? 'Editar Curso' : 'Agregar Curso';
  }

  ngOnInit(): void {
    this.obtenerProfesores(); // Fetch professors when the component initializes
  }

  cerrarModal() {
    this.dialogRef.close();
  }

  obtenerProfesores() {
    this.profesorService.obtenerProfesores().subscribe({
      next: (response) => {
        this.profesores = response;
      },
      error: (error) => {
        console.error('Error al obtener los profesores:', error);
      }
    });
  }

  guardarCurso() {
    console.log('Datos del curso antes de enviar:', this.curso);
    this.cursoService.agregarCurso(this.curso).subscribe({
      next: (response) => {
        console.log('Curso guardado con éxito:', response);
        this.snackBar.open('Curso guardado con éxito', 'Cerrar', {
          duration: 3000
        }).afterDismissed().subscribe(() => {
          this.dialogRef.close(response);
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/curso']);
          });
        });
      },
      error: (error) => {
        console.error('Error al guardar el curso:', error);
      }
    });
  }
}