import { Component, OnInit } from '@angular/core';
import { Receta } from 'src/app/Receta.model';
import { Router } from '@angular/router';
import { Ingredientes } from 'src/app/Ingredientes.model';
import { NavbarService } from 'src/app/sharepage/navbar/navbar-service.service';
import { Usuarios } from 'src/app/Usuarios.model';
import { recetasService } from 'src/app/pages/mostrar-recetas/recetasService.service';

/**
 * Componente que representa la barra de navegación de la aplicación.
 */
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {


  /**
   * Variable para almacenar las categorias de los ingredientes
   */
  categoria: boolean;

  /**
  * Array de Recetas para almacenar las recetas que llegan del Backend
  */
  recetas:Receta[] = [];

  /**
  * Array de Ingredientes para almacenar los ingredientes que llegan del Backend
  */
  ingredientes:Ingredientes[] = [];

  /**
   * Almacena el tipo de ingrediente actual para que este el menu ordenado
   */
  tipoActual: string = '';

  /**
 * Objeto usuario para recibirlo en la página si proviene de haberse registrado
 */
  usuario: Usuarios;

/**
 * Variable para mostrar la lista de ingredientes de 8 en 8
 */
  ingredientesAMostrar:number = 8;

/**
 * Array para almacenar los tipos de ingredientes sin repetir
 */
  tiposUnicos:string [];

/**
 * Array para almacenar los ingredientes de un tipo
 */
  ingredientesXTipo: Ingredientes[];

/**
 * Variable para saber si el ingredeinte ha sido seleccionado
 */
  tipoSeleccionado: string;


   /**
    * Injectamos los servicios necesarios para el funcionamiento de la página
    * @param NavbarService Servicio del componente para la obtención de los ingredientes
    * @param router Para el envio del Usuario a otras páginas
    */
    constructor(private NavbarService: NavbarService, private router: Router, private recetasService: recetasService) {

    }

    /**
   * Metodo que se carga al iniciar la página
   * 
   * LLamamos al metodo obtener ingredientes para obtener los ingredientes y a la vez 
   * 
   * que los recibimos los filtramos para obtener los tipos metiendolos en un array sin repetirlos
   * 
   * 
   */
    ngOnInit(): void {
        this.NavbarService.obtenerIngredientes(null).subscribe(ingredientes=>
          {this.ingredientes = ingredientes;

          const tipos = this.ingredientes.map(
            (ingrediente) => ingrediente.tipo
          );
          this.tiposUnicos = tipos.filter(
            (tipo, index) => tipos.indexOf(tipo) === index
          );
        });
          
    }

    /**
     * Método para recuperar de la base de datos los ingredientes por su tipo y controlar la pulsación del tipo de ingrediente
     * 
     * reseteamos el número de ingredientes a mostrar para que pueda ser visible en las demás iteraciones
     * @param tipoIngrediente recibimos el tipo de los ingredientes que se quieren mostrar
     */
    tipo(tipoIngrediente) {
      if (tipoIngrediente === this.tipoActual) {
        this.tipoActual = null;
      } else {
      this.tipoActual = tipoIngrediente;
      this.ingredientesAMostrar = 8;
      this.recetasService.obtenerTodosIngredientes(tipoIngrediente).subscribe((ingredientesXTipo) => {
        this.ingredientesXTipo = ingredientesXTipo.filter((ingrediente) => ingrediente.tipo === tipoIngrediente);
      });
    }
    }

  
  /**
   * Método para añadir al array de ingredientes los ingredientes que seleccione el usuario
   * @param ingXtipo recibimos el ingrediente que se ha seleccionado en el check box
   */
ingSelect(ingXtipo){
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
     * Metodo para pasar el array de idIngrediente
     */
    obtenerRecetasIngredientes(){
      const nuevosIngredientes: Ingredientes[] = [];

      for (let i = 0; i < this.ingredientes.length; i++) {
        if (this.ingredientes[i].seleccionado) {
          const idIngrediente = this.ingredientes[i].idIngredientes;
          console.log(this.ingredientes[i]);
          const nuevoIngrediente = new Ingredientes(idIngrediente, '', '', '', [], false);
          nuevosIngredientes.push(nuevoIngrediente);
        }
      }
      
      this.router.navigate(['/mostrarRecetasIngredientes'], { state: { usuario:this.usuario, idIngredientes: nuevosIngredientes } });
    }

}
