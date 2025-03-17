import { Component, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { StudentService } from '../../services/student.service';

@Component({
  selector: 'app-student-modal',
  templateUrl: './student-modal.component.html',
  styleUrls: ['./student-modal.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatDialogModule]
})
export class StudentModalComponent {
  titulo: string;
  student = {
    type: 'estudiante',
    nombre: '',
    apellido: '',
    fechaNacimiento: '',
    email: '',
    telefono: '',
    numeroMatricula: '',
    grado: null
  };

  constructor(
    private dialogRef: MatDialogRef<StudentModalComponent>,
    private studentService: StudentService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.student = data.Student ? { ...data.Student } : {};
    this.titulo = data.Student ? 'Editar Estudiante' : 'Agregar Estudiante';
  }

  cerrarModal() {
    this.dialogRef.close();
  }

  guardarStudent() {
    this.student.type = 'estudiante';
    console.log('Datos del estudiante antes de enviar:', this.student);
    this.studentService.agregarStudent(this.student).subscribe({
      next: (response) => {
        console.log('estudiante guardado con éxito:', response);
        this.dialogRef.close(response);
      },
      error: (error) => {
        console.error('Error al guardar el estudiante:', error);
      }
    });
  }

}
