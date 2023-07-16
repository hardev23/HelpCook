import { Component, OnInit } from '@angular/core';
import { Receta } from 'src/app/Receta.model';
import { recetasService } from './recetasService.service';
import { Usuarios } from 'src/app/Usuarios.model';
import { Router } from '@angular/router';
import { FechaPipe } from 'src/app/fecha.pipe';

/**
 * Componente para la Página de mostrar Recetas
 * 
 * Se mostrarán las recetas según la opción elegida por el usuario
 */
@Component({
  selector: 'app-mostrar-recetas',
  templateUrl: './mostrar-recetas.component.html',
  styleUrls: ['./mostrar-recetas.component.css'],
  providers: [FechaPipe]
})

export class MostrarRecetasComponent implements OnInit {
  /**
   * Variable para almacenar la opción de la categoria elegida por el usuario que recibimos del Servicio
   */
  recetasXCategoria :string;

  /**
   * Variable para almacenar la opción de las recetass mejor valoradas o más recientes que haya elegido el usuario que recibimos del Servicio
   */
  recetasAMostrar: string;

  /**
   * Variable para controlar las recetas que se deben mostrar en la página, por categoría u ordenación
   */
  categoria: boolean;
  
 /**
  * Array de Recetas para almacenar las recetas que llegan del Backend a través del servicio (dataService)
  */
  recetas:Receta[] = [];

/**
 * Objeto usuario para recibirlo en la página si proviene de haberse registrado
 */
  usuario: Usuarios;
    
  /**
   * Injectamos los servicios para pasar y recibir datos a otro componente y para la conexión con el Backend
   * @param router Para el envio del Usuario a otras páginas
   * @param recetasService. Servicio para conectar con el Backend
   */
  constructor( private router: Router,
    private recetasService: recetasService) {   

    const navigationState = history.state;
    this.usuario = navigationState.usuario;
    this.recetasXCategoria = navigationState.categoria;
    this.recetasAMostrar = navigationState.recetasAMostrar;
      
  }

  /**
   * Método que se carga cuando inicia la página
   * 
   * Si recibe un valor para filtrar por categoría mostrará las recetas por la cátegoria que recibe, 
   * mandamos al método para obtener las recetas del servicio el valor recibido
   * y almacenamos en el array de recetas las recetas que recibimos de éste para mostrarlas
   * 
   * Si no recibimos valor por categoría mandaremos al método de obtener recetas del Servicio
   *  el filtro para mostrar las recetas ordenadas según el párametro que se reciba,
   * almacenamos en el array de recetas las recetas que recibimos para poder mostrarlas
   * 
   */
  ngOnInit(): void {
    if(this.recetasXCategoria!=null){
      this.categoria=false;
   this.recetasService.obtenerTodos(this.recetasXCategoria,null,null,null).subscribe(recetas=>
    {this.recetas = recetas;});
     
   }else{
    this.categoria=true;
    this.recetasService.obtenerTodos(null,null,null,this.recetasAMostrar).subscribe(recetas=>
      {this.recetas = recetas;});
     
   }
    }
    
    /**
     * Método para mandar el usuario a Inicio si se está registrado
     */
    volverInicio(){
      this.router.navigate([''],{state:{usuario:this.usuario}});
    }

    /**
     * Método para mandar el usuario si está registrado y la recetas que se quiere visualizar a la página Mostrar una receta
     * @param recetaId 
     */
    mandarUsuarioReceta(recetaId: number){
      console.log(recetaId)
      this.router.navigate(['/mostraUnaReceta'],{state:{usuario:this.usuario,recetaId:recetaId }});
    }
  
  }
 
