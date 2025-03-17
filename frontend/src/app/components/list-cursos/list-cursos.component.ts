import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { CursoModalComponent } from '../curso-modal/curso-modal.component';
import { Course, CourseService } from '../../services/course.service';
import { ProfesorService, Profesor } from '../../services/profesor.service';

@Component({
  selector: 'app-list-cursos',
  imports: [CommonModule, NgFor, NgIf, FormsModule],
  templateUrl: './list-cursos.component.html',
  styleUrls: ['./list-cursos.component.css']
})
export class ListCursosComponent implements OnInit {
  curso: Course[] = [];
  profesores: Map<number, string> = new Map(); // Map to store professor names by ID

  constructor(
    private cursoService: CourseService,
    private profesorService: ProfesorService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.obtenerCursos();
    this.obtenerProfesores();
  }

  obtenerCursos(): void {
    this.cursoService.obtenerCursos().subscribe({
      next: (data) => {
        this.curso = data;
      },
      error: (err) => {
        console.error('Error al obtener los cursos:', err);
      }
    });
  }

  obtenerProfesores(): void {
    this.profesorService.obtenerProfesores().subscribe({
      next: (profesores) => {
        profesores.forEach(profesor => {
          this.profesores.set(profesor.id_Persona, `${profesor.nombre} ${profesor.apellido}`);
        });
      },
      error: (err) => {
        console.error('Error al obtener los profesores:', err);
      }
    });
  }

  getNombreProfesor(idProfesor: number): string {
    return this.profesores.get(idProfesor) || 'Desconocido';
  }

  agregarcurso() {
    this.dialog.open(CursoModalComponent, {
      width: '500px',
      disableClose: true,
      data: {}
    });
  }

  editarcurso(id: number) {
    this.cursoService.obtenerCursoPorId(id).subscribe({
      next: (curso) => {
        const dialogRef = this.dialog.open(CursoModalComponent, {
          width: '500px',
          data: { curso }
        });

        dialogRef.afterClosed().subscribe(result => {
          if (result) {
            this.cursoService.editarCurso(result).subscribe(() => {
              this.ngOnInit();
            });
          }
        });
      },
      error: (err) => {
        console.error('Error al obtener el curso:', err);
      }
    });
  }

  eliminarcurso(id: number) {
    if (confirm('¿Está seguro de eliminar este curso?')) {
      this.cursoService.eliminarCurso(id).subscribe({
        next: () => {
          this.curso = this.curso.filter(p => p.id_curso !== id);
        },
        error: (err) => {
          console.error('Error al eliminar el curso:', err);
        }
      });
    }
  }
}
