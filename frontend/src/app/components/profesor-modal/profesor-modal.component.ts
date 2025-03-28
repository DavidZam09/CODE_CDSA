import { Component, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ProfesorService } from '../../services/profesor.service';

@Component({
  selector: 'app-profesor-modal',
  templateUrl: './profesor-modal.component.html',
  styleUrls: ['./profesor-modal.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatDialogModule]
})
export class ProfesorModalComponent {
  titulo: string;
  profesor = {
    id_Persona: 0,
    type: 'profesor',
    nombre: '',
    apellido: '',
    fechaNacimiento: '',
    email: '',
    telefono: '',
    especialidad: '',
    fechaContratacion: ''
  };

  constructor(
    private dialogRef: MatDialogRef<ProfesorModalComponent>,
    private profesorService: ProfesorService,
    private snackBar: MatSnackBar,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.profesor = data.profesor ? { ...data.profesor } : {};
    this.titulo = data.profesor ? 'Editar Profesor' : 'Agregar Profesor';
  }

  cerrarModal() {
    this.dialogRef.close();
  }

  guardarProfesor() {
    this.profesor.type = 'profesor';
    console.log('Datos del profesor antes de enviar:', this.profesor);
    this.profesorService.agregarProfesor(this.profesor).subscribe({
      next: (response) => {
        console.log('Profesor guardado con éxito:', response);
        this.snackBar.open('Profesor guardado con éxito', 'Cerrar', {
          duration: 3000
        }).afterDismissed().subscribe(() => {
          this.dialogRef.close(response);
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/profesor']);
          });
        });
      },
      error: (error) => {
        console.error('Error al guardar el profesor:', error);
      }
    });
  }
}