import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfesorModalComponent } from './profesor-modal.component';

describe('ProfesorModalComponent', () => {
  let component: ProfesorModalComponent;
  let fixture: ComponentFixture<ProfesorModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfesorModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfesorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
