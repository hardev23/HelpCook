import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarUnaRecetaComponent } from './mostrar-una-receta.component';

describe('MostrarUnaRecetaComponent', () => {
  let component: MostrarUnaRecetaComponent;
  let fixture: ComponentFixture<MostrarUnaRecetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MostrarUnaRecetaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostrarUnaRecetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
