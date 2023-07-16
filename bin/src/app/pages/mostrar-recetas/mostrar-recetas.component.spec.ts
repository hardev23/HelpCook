import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarRecetasComponent } from './mostrar-recetas.component';

describe('MostrarRecetasComponent', () => {
  let component: MostrarRecetasComponent;
  let fixture: ComponentFixture<MostrarRecetasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MostrarRecetasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostrarRecetasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
