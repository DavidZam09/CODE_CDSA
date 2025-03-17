import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmonModalComponent } from './admon-modal.component';

describe('AdmonModalComponent', () => {
  let component: AdmonModalComponent;
  let fixture: ComponentFixture<AdmonModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdmonModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmonModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
