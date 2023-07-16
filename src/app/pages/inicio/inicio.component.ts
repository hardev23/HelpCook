import { Component, OnInit} from '@angular/core';
import { recetasService } from '../mostrar-recetas/recetasService.service';
import { Receta } from 'src/app/Receta.model';
import { Usuarios } from 'src/app/Usuarios.model';
import { Router } from '@angular/router';

/**
 * Componente para la página de Inicio
 */
@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit{

/**
 * Variable para alamacenar el filtro de
 * mejor valoradas o más recientes para su posterior ordenación.
 */
  recetasAMostrar: string;

/**
 * Variable para almacenar el filtro de recetas por categoria.
 */
  recetasXCategoria:string;

  /**
   * Variable para almacenar la lista de recetas mejor valoradas
   */
  recetasMejorValoradas: Receta[];

  /**
   * Variable para almacenar la lista de recetas más recientes
   */
  recetasMasRecientes: Receta[];

  /**
   * objeto Usuario para recibir al usuario si llega a esta página una vez registrado
   */
  usuario: Usuarios;
 
/**
 * Injectamos los servicios
 * 
 * @param recetasService Servicio para hacer la consulta al backend
 * @param router Para el envio del Usuario a otras páginas
 */
  constructor(private router: Router, private recetasService: recetasService ){

  }
  
  /**
   * Método que se carga cuando inicia la página
   */
  ngOnInit(): void {
  
    this.usuario = history.state.usuario;
    
    this.recetasService.obtenerTodos(null,null,null,"Recetas mejor valoradas").subscribe(recetas=>
      {this.recetasMejorValoradas = recetas;
      
     
      });

      this.recetasService.obtenerTodos(null,null,null,"Recetas más recientes").subscribe(recetas=>
        {this.recetasMasRecientes = recetas;
          });
  }

  /**
   * Método para capturar el tipo de recetas a ordenar.
   * 
   * Asignamos a nuestra variable el texto que recibimos del elemento,
   * mandamos al método de nuestro servicio para conectar componentes la variable que acabamos de capturar.
   * @param evento. Recibimos el click sobre la categoria de la receta
   */
  capturarTexto(evento :MouseEvent) {
    this.recetasAMostrar = (evento.target as HTMLElement).textContent;

    console.log(this.recetasAMostrar);
    this.router.navigate(['/mostrarRecetas'],{state:{usuario:this.usuario,recetasAMostrar:this.recetasAMostrar}});
  }

  /**
   * Método para capturar la categoria de lass recetas a mostar.
   * 
   * Asignamos a nuestra variable el texto del atributo alt del elemento,
   * mandamos al método de nuestro servicio para conectar componentes la variable que acabamos de capturar.
   * @param event. Recibimos el click sobre el elemento a mostrar
   */
  capturarTextoCategorias(event: any) {
    this.recetasXCategoria = event.target.alt;
    this.router.navigate(['/mostrarRecetas'],{state:{usuario:this.usuario,categoria:this.recetasXCategoria}});
  }

  /**
   * Método para el routing a la Página de mostrar una receta mandandole el usuario si está registrado y la receta que quiere visualizar
   * @param recetaId Recibimos el id de la receta que queremos visualizar
   */
  mandarUsuarioReceta(recetaId: number){
    console.log(recetaId)
    this.router.navigate(['/mostraUnaReceta'],{state:{usuario:this.usuario,recetaId:recetaId }});
  }

}
