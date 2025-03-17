import { Component, OnInit } from '@angular/core';
import { Administrativo, AdmonService } from '../../services/admon.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AdmonModalComponent } from '../admon-modal/admon-modal.component';

@Component({
  selector: 'app-list-admons',
  imports: [CommonModule, NgFor, NgIf, FormsModule],
  templateUrl: './list-admons.component.html',
  styleUrl: './list-admons.component.css'
})
export class ListAdmonsComponent implements OnInit {
  admon: Administrativo[] = [];

  constructor(private admonService: AdmonService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.admonService.obtenerAdministrativos().subscribe({
      next: (data) => {
        this.admon = data;
      },
      error: (err) => {
        console.error('Error al obtener administrativo:', err);
      }
    });
  }
  agregarAdmon() {
    this.dialog.open(AdmonModalComponent, {
      width: '500px',
      disableClose: true,
      data: {}
    });
  }

  obteneradmon(): void {
    this.admonService.obtenerAdministrativos().subscribe({
      next: (admon) => {
        this.admon = admon;
      },
      error: (err) => {
        console.error('Error al obtener los admon', err);
      }
    });
  }

  editarAdmon(id: number) {
    this.admonService.obtenerAdministrativoPorId(id).subscribe({
      next: (admon) => {
        const dialogRef = this.dialog.open(AdmonModalComponent, {
          width: '500px',
          data: { admon }
        });

        dialogRef.afterClosed().subscribe(result => {
          if (result) {
            this.admonService.editarAdministrativo(result).subscribe(() => {
              this.ngOnInit();
            });
          }
        });
      },
      error: (err) => {
        console.error('Error al obtener el admon:', err);
      }
    });
  }


  eliminarAdmon(id: number) {
    if (confirm('¿Está seguro de eliminar este admon?')) {
      this.admonService.eliminarAdministrativo(id).subscribe({
        next: () => {
          this.admon = this.admon.filter(p => p.id_Persona !== id);
        },
        error: (err) => {
          console.error('Error al eliminar el admon:', err);
        }
      });
    }
  }
}
