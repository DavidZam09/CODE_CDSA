import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAdmonsComponent } from './list-admons.component';

describe('ListAdmonsComponent', () => {
  let component: ListAdmonsComponent;
  let fixture: ComponentFixture<ListAdmonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListAdmonsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAdmonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
