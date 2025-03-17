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
import { AdmonService } from '../../services/admon.service';

@Component({
  selector: 'app-admon-modal',
  templateUrl: './admon-modal.component.html',
  styleUrls: ['./admon-modal.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatDialogModule]
})
export class AdmonModalComponent {
  titulo: string;
  today: string;
  admon = {
    id_Persona: 0,
    type: 'administrativo',
    nombre: '',
    apellido: '',
    fechaNacimiento: '',
    email: '',
    telefono: '',
    cargo: '',
    departamento: ''
  };

  constructor(
    private dialogRef: MatDialogRef<AdmonModalComponent>,
    private admonService: AdmonService,
    private snackBar: MatSnackBar,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: any

  ) {
    const now = new Date();
    this.today = now.toISOString().split('T')[0]; // Format as YYYY-MM-DD
    this.admon = data.admon ? { ...data.admon } : {};
    this.titulo = data.admon ? 'Editar Administrativo' : 'Agregar Administrativo';
  }

  cerrarModal() {
    this.dialogRef.close();
  }

  guardaradmon() {
    this.admon.type = 'administrativo';
    console.log('Datos del admon antes de enviar:', this.admon);
    this.admonService.agregarAdministrativo(this.admon).subscribe({
      next: (response) => {
        console.log('admon guardado con éxito:', response);
        this.snackBar.open('admon guardado con éxito', 'Cerrar', {
          duration: 3000
        }).afterDismissed().subscribe(() => {
          this.dialogRef.close(response);
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/admon']);
          });
        });
      },
      error: (error) => {
        console.error('Error al guardar el admon:', error);
      }
    });
  }
}