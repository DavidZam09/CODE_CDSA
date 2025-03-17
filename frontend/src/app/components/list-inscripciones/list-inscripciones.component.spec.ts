import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListInscripcionesComponent } from './list-inscripciones.component';

describe('ListInscripcionesComponent', () => {
  let component: ListInscripcionesComponent;
  let fixture: ComponentFixture<ListInscripcionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListInscripcionesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListInscripcionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
