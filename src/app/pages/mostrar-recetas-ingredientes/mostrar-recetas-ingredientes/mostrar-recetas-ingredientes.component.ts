import { Component, NgModule, OnInit } from '@angular/core';
import { Receta } from 'src/app/Receta.model';
import { Ingredientes } from 'src/app/Ingredientes.model';
import { Usuarios } from 'src/app/Usuarios.model';
import { Router } from '@angular/router';
import { recetasService } from '../../mostrar-recetas/recetasService.service';


/**
 * Componente para la página donde se mostrarán las recetas filtradas por ingredientes
 */
@Component({
  selector: 'app-mostrar-recetas-ingredientes',
  templateUrl: './mostrar-recetas-ingredientes.component.html', 
  styleUrls: ['./mostrar-recetas-ingredientes.component.css']
})
export class MostrarRecetasIngredientesComponent implements OnInit{

  /**
   * Variable para almacenar la opción de los ingredientes que queremos obtener
   */
  ingredientesSeleccionados: Ingredientes[] = [];

  /**
  * Array de Recetas para almacenar las recetas que llegan del Backend
  */
  recetas:Receta[] = [];

  /**
 * Objeto usuario para recibirlo en la página si proviene de haberse registrado
 */
  usuario: Usuarios;

  /**
   * Injectamos los servicios necesarios para el funcionamiento de la página
   * @param recetasService Servicio para la conexion con el back para la recepción de las recetas
   * @param router  Para el envio del Usuario a otras páginas
   */
  constructor(private recetasService: recetasService, private router: Router) { 
    this.usuario=null;
  }

  /**
   * Método que se carga cuando inicia la página
   *
   * LLamamos al metodo obtener recetas y le pasamos el valor de ingredientes seleccionados para
   * que nos pase solo las recetas que contienen los ingredientes que ha seleccionado el usuario
   *
   */
  ngOnInit(): void {
    const navigationState = history.state;
    this.usuario = history.state.usuario;
    this.ingredientesSeleccionados = navigationState.idIngredientes;    

    console.log(this.ingredientesSeleccionados);
    this.recetasService.obtenerTodos(null,this.ingredientesSeleccionados,null, null).subscribe(recetas=>
      {this.recetas = recetas;});

  }

  /**
     * Método para mandar el usuario si está registrado y la recetas que se quiere visualizar a la página Mostrar una receta
     * @param recetaId 
     */
  mandarUsuarioReceta(recetaId: number){
  
    this.router.navigate(['/mostraUnaReceta'],{state:{usuario:this.usuario,recetaId:recetaId }}); 
  }

    /**
   * Método para el routing a la página de inicio mandando al Usuario
   */
  volverInicio(){
    this.router.navigate([''],{state:{usuario:this.usuario}});
  }

}


