import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { ProfesorService, Profesor } from '../../../services/profesor.service';
import { ProfesorModalComponent } from '../../profesor-modal/profesor-modal.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-profesor-list',
  standalone: true, // 🔹 Agregar esta línea
  templateUrl: './profesor-list.component.html',
  imports: [CommonModule, NgFor, NgIf],
  styleUrls: ['./profesor-list.component.css']
})
export class ProfesorListComponent implements OnInit {
  profesores: Profesor[] = [];

  constructor(private profesorService: ProfesorService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.profesorService.obtenerProfesores().subscribe({
      next: (data) => {
        this.profesores = data;
      },
      error: (err) => {
        console.error('Error al obtener profesores:', err);
      }
    });
  }
  agregarProfesor() {
    this.dialog.open(ProfesorModalComponent, {
      width: '500px',
      disableClose: true,
      data: {}
    });
  }

  obtenerProfesores(): void {
    this.profesorService.obtenerProfesores().subscribe({
      next: (profesores) => {
        this.profesores = profesores;
      },
      error: (err) => {
        console.error('Error al obtener los profesores', err);
      }
    });
  }

  editarProfesor(id: number) {
    this.profesorService.obtenerProfesorPorId(id).subscribe({
      next: (profesor) => {
        const dialogRef = this.dialog.open(ProfesorModalComponent, {
          width: '500px',
          data: { profesor }
        });

        dialogRef.afterClosed().subscribe(result => {
          if (result) {
            this.profesorService.editarProfesor(result).subscribe(() => {
              this.ngOnInit();
            });
          }
        });
      },
      error: (err) => {
        console.error('Error al obtener el profesor:', err);
      }
    });
  }


  eliminarProfesor(id: number) {
    if (confirm('¿Está seguro de eliminar este profesor?')) {
      this.profesorService.eliminarProfesor(id).subscribe({
        next: () => {
          this.profesores = this.profesores.filter(p => p.id_Persona !== id);
        },
        error: (err) => {
          console.error('Error al eliminar el profesor:', err);
        }
      });
    }
  }
}
