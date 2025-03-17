import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { StudentService, Student } from '../../../services/student.service';
import { StudentModalComponent } from '../../student-modal/student-modal.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-student-list',
  standalone: true, // 🔹 Agregar esta línea
  templateUrl: './student-list.component.html',
  imports: [CommonModule, NgFor, NgIf],
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  students: Student[] = [];

  constructor(private studentService: StudentService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.studentService.obtenerStudents().subscribe({
      next: (data) => {
        this.students = data;
      },
      error: (err) => {
        console.error('Error al obtener Studentes:', err);
      }
    });
  }
  agregarStudent() {
    this.dialog.open(StudentModalComponent, {
      width: '500px',
      disableClose: true,
      data: {}
    });
  }

  editarStudent(id: number) {
    this.studentService.ObtenerStudentById(id).subscribe({
      next: (Student) => {
        const dialogRef = this.dialog.open(StudentModalComponent, {
          width: '500px',
          data: { Student }
        });

        dialogRef.afterClosed().subscribe(result => {
          if (result) {
            this.studentService.editarStudent(result).subscribe(() => {
              this.ngOnInit();
            });
          }
        });
      },
      error: (err) => {
        console.error('Error al obtener el Student:', err);
      }
    });
  }


  eliminarStudent(id: number) {
    if (confirm('¿Está seguro de eliminar este Student?')) {
      this.studentService.eliminarStudent(id).subscribe({
        next: () => {
          this.students = this.students.filter(p => p.id_Persona !== id);
        },
        error: (err) => {
          console.error('Error al eliminar el Student:', err);
        }
      });
    }
  }
}
