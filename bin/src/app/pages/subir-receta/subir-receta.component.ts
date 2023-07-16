import { Component, EventEmitter } from '@angular/core';
import {  Router } from '@angular/router';
import { recetasService } from '../mostrar-recetas/recetasService.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Ingredientes } from 'src/app/Ingredientes.model';
import { Pasos } from 'src/app/Pasos.model';
import { RecetasRequest } from 'src/app/RecetasRequest.model';
import { Usuarios } from 'src/app/Usuarios.model';

/**
 * Componente para crear una receta
 *
 * Se creará una receta con los datos introducidos por el usuario
 */
@Component({
  selector: 'app-subir-receta',
  templateUrl: './subir-receta.component.html',
  styleUrls: ['./subir-receta.component.css'],
})
export class SubirRecetaComponent {
  /**
   * Variable para almacenar el Usuario que sube la receta
   */
  usuario: Usuarios;

  /**
   * Variable para almacenar la descripcion de la receta
   */
  descripcion: string;

  /**
   * Variable para alamacenar el tiempo de preparación de la receta
   */
  tiempo: number;

  /**
   * Variable para almacenar la foto de la receta
   */
  foto: string;

  /**
   * Variable para almacenar el titulo de la receta
   */
  titulo: string;

  /**
   * Variable para almacenar la categoría de la receta
   */
  categoria: string;

  /**
   * Variable para capturar la fecha en que se está subiendo la receta
   */
  fechaAlta: Date;

  /**
   * Variable para saber la valoración media de la receta, como está recien subida siempre valdrá 0
   */
  valoracionMedia: number = 0;

  /**
   * Variable para almacenar para cuantos comensales es la receta
   */
  comensales: number;

  /**
   * Array para almacenar todos los pasos
   */
  pasos: Pasos[];

  /**
   * Variable para mostrar el div de la inserción del paso
   */
  mostrarPaso: boolean = false;

  /**
   * Variable para almacenar el número de pasos insertados
   */
  contadorPasos: number = 0;

  /**
   * Array para recuperar todos los ingredientes de la base de datos
   */
  ingredientesMostrar: Ingredientes[];

  /**
   * Variable para recuperar y poder mostrar los ingredientes por tipo
   */
  ingredientesXTipo: Ingredientes[];

  /**
   * Array para mostrar los tipos de ingredientes que existen
   */
  tiposUnicos: string[] = [];

  /**
   * Variable para almacenar la selección del tipo de ingrediente
   */
  tipoSeleccionado: string;

  /**
   * Array que contendrá los ingredientes contendrá la receta
   */
  ingredientes: Ingredientes[];

  /**
   * Variable para controlar los ingredeinets que se muestran
   */
  numIngrAMostrar: number = 5;

  /**
   * Variable para mostrar si la receta se ha agregado a la base de datos
   */
  subidaCorrectamente: boolean;

  /**
   * Variable para almacenar la categoria de la receta seleccionada por el usuario
   */
  categoriaSeleccionada: any = null;

  /**
   * Constructor de la clase donde donde Injectamos los servicios necesarios
   * @param router Routing para poder recibir el Usuario que va a subir la receta
   * @param recetasService Servicio necesario para la optención de los datos de las recetas y los ingredientes
   */
  constructor(
    private recetasService: recetasService,
    private router: Router
  ) {
    this.ingredientes = [];
    this.pasos = [];
  }

  /**
   * Método que se carga cuando inicia la página
   *
   * Recuperamos el id del usuario de la ruta y se lo asignamos a nuestra variable idUsuario
   *
   * Obtenemos todos los ingredientes de la base de datos mandando la consulta a nuestro servicio,
   * recorremos el array de ingredientes para optener los tipos de éstos y  guardamos los tipos no repetidos en nuestra variable tipos únicos.
   *
   * Creamos una variable Date para almacenar la fecha actual cuando ingresa en la página, recogemos el año, mes y dia de esa variable,
   * y se la asignamos a la variable que vamos a mandar a la receta en el formato que necesitamos
   */
  ngOnInit(): void {
    this.usuario = history.state.usuario;

    this.recetasService
      .obtenerTodosIngredientes(null)
      .subscribe((ingredientesMostrar) => {
        this.ingredientesMostrar = ingredientesMostrar;

        const tipos = this.ingredientesMostrar.map(
          (ingrediente) => ingrediente.tipo
        );
        this.tiposUnicos = tipos.filter(
          (tipo, index) => tipos.indexOf(tipo) === index
        );
      });

    const fechaActual = new Date();
    const anyo = fechaActual.getFullYear().toString();
    let mes = (fechaActual.getMonth() + 1).toString();
    if (mes.length === 1) {
      mes = '0' + mes;
    }
    let dia = fechaActual.getDate().toString();
    if (dia.length === 1) {
      dia = '0' + dia;
    }
    let hora = fechaActual.getHours().toString();
    if (hora.length === 1) {
      hora = '0' + hora;
    }
    let minutos = fechaActual.getMinutes().toString();
    if (minutos.length === 1) {
      minutos = '0' + minutos;
    }
    let segundos = fechaActual.getSeconds().toString();
    if (segundos.length === 1) {
      segundos = '0' + segundos;
    }

    this.fechaAlta = new Date(`${anyo}-${mes}-${dia}T${hora}:${minutos}:${segundos}`);
  
  }

  /**
   * Método para pasar la foto de la receta a Base 64
   * y poder guardarlas como string
   */
  onFileSelected(event: any) {
    const file = event.target.files[0];

    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.foto = reader.result as string;
      };
    }
  }

  /**
   * Método para capturar la categoría de la receta y cambiar el color de la categoria seleccionada
   */
  capturarCategorias(event: any) {
    const botonSeleccionado = event.currentTarget;
    const botones = document.querySelectorAll('.btn-iconosbarra');

    // Si hay un botón seleccionado, lo deseleccionamos
    if (this.categoriaSeleccionada) {
      this.categoriaSeleccionada.classList.remove(
        'btn-iconosbarra-seleccionado'
      );
    }

    this.categoria = botonSeleccionado.querySelector('img').alt;
    botonSeleccionado.classList.add('btn-iconosbarra-seleccionado');
    this.categoriaSeleccionada = botonSeleccionado;
  }

  /**
   * Método para recuperar los ingredientes por el tipo que elija el Usuario
   * recuperamos la seleccion y la mandamos al servicio pasandole el tipo de los ingredientes que queremos recuperar,
   * obteniendo un array de ingredientes del tipo seleccionado
   * @param event Recibimos la selección del usuario
   */
  tipo(event: any) {
    const tipoSeleccionado = event.target.value;
    this.tipoSeleccionado = tipoSeleccionado;
    this.numIngrAMostrar = 5;
    this.recetasService
      .obtenerTodosIngredientes(tipoSeleccionado)
      .subscribe((ingredientesXTipo) => {
        this.ingredientesXTipo = ingredientesXTipo;
      });
  }

  /**
   * Método para mostar o no el input de cantidad del ingrediente seleccionado,
   * si el ingrediente se selecciona lo añade al array de ingredientes para asociarlo a la receta,
   * si lo deselecciona lo borra del array
   * @param ingXtipo
   */
  ingSelect(ingXtipo) {
    ingXtipo.seleccionado = !ingXtipo.seleccionado;

    if (ingXtipo.seleccionado) {
      this.ingredientes.push(ingXtipo);
    } else {
      const index = this.ingredientes.findIndex(
        (i) => i.idIngredientes === ingXtipo.idIngrediente
      );
      this.ingredientes.splice(index, 1);
    }
  }
  /**
   * Método para asignar la cantidad al ingrediente seleccionado
   * @param cantidad. Recibe la cantidad ingresada por el usuario
   * @param ingXTipo. Recibe el ingrediente para asociarle la cantidad
   */
  setCantidad(cantidad: any, ingXTipo: Ingredientes) {
    const ingredienteseleccionado = this.ingredientes.find(
      (i) => i.idIngredientes === ingXTipo.idIngredientes
    );
    if (typeof cantidad === 'string') {
      ingXTipo.cantidad = cantidad;
    }
  }

  /**
   * Método para mostrar el div del paso cuando se le de al botón y contabilizar los pasos agregados
   */
  mostrarPasos() {
    this.mostrarPaso = true;
    this.contadorPasos++;
  }

  mostrarIngredientes(){
    this.numIngrAMostrar += 5;
  }

  /**
   * Método que se recibe del componente hijo el paso con los datos capturados
   *
   * para añadir al array de pasos un nuevo paso con los datos recibidos
   * @param paso. Recibimos un array con los datos que nos interesan para creación del paso.
   */
  onPasoAgregado(paso: { descripcion: string; foto: string }) {
    const tipo = this.pasos.length + 1;

    console.log(tipo);

    this.pasos.push(new Pasos(null, null, tipo, paso.descripcion, paso.foto));
  }

  /**
   * Método para mandar al servicio los datos rellenados por el usuario y poder guardar la receta
   */
  subirReceta() {
    let receta = new RecetasRequest(
      null,
      this.usuario.idUsuarios,
      this.descripcion,
      this.tiempo,
      this.foto,
      this.titulo,
      this.categoria,
      this.fechaAlta,
      this.valoracionMedia,
      this.comensales,
      this.ingredientes,
      this.pasos
    );

    this.recetasService.subirReceta(receta).subscribe(
      (response) => (this.subidaCorrectamente = true),

      (error: HttpErrorResponse) => {
        if (error.error instanceof ErrorEvent) {
          this.subidaCorrectamente = false;
        } else {
          this.subidaCorrectamente = false;
        }
      }
    );
    alert('Receta subida correctamente.');
    this.router.navigate([''], { state: { usuario: this.usuario } });
  }
}
