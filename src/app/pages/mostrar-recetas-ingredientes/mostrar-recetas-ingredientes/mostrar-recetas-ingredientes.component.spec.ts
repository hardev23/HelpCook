import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarRecetasIngredientesComponent } from './mostrar-recetas-ingredientes.component';

describe('MostrarRecetasIngredientesComponent', () => {
  let component: MostrarRecetasIngredientesComponent;
  let fixture: ComponentFixture<MostrarRecetasIngredientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MostrarRecetasIngredientesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostrarRecetasIngredientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
