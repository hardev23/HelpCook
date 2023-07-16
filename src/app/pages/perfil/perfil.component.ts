import { Component, OnInit } from '@angular/core';
import { Usuarios } from 'src/app/Usuarios.model';
import { recetasService } from '../mostrar-recetas/recetasService.service';
import { Receta } from 'src/app/Receta.model';
import { RegistroService } from '../registro/registro-service.service';
import { Router } from '@angular/router';

/**
 * Componente para la página el perfil del Usuario
 *
 * Se mostrará los datos del Usuario que acaba de acceder
 */
@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit{

  /**
   * Objeto para mostrar el usuario
   */
  usuario: Usuarios;

  /**
   * Array de objetos Recetas subidas por el Usuario
   */
  recetas: Receta[];

  /**
   * Array de Recetas que tiene guardadas el usuario como Favoritos
   */
  recetasFavoritos: Receta[];

  /**
   * Variable para controlar el número de recetas que se muestran de los arrays de recetas
   */
  numRecetasMostradas: number = 4;
 
  
/**
 *  Injectamos los servicios necesarios para el funcionamiento de la página
 * 
 * @param recetasService Servicio para conectar con el backEnd
 * @param registroService Servicio para conectar con el backEnd
 * @param router Para el envio del Usuario a otras páginas
 */
  constructor(private recetasService: recetasService, private registroService: RegistroService,
    private router: Router) {
    
  

  }
  /**
   * Método que se carga cuando se inicializa la página
   * 
   * recibimos el usuario del loggin y recuperamos sus recetas guardadas y recetas subidas
   */
  ngOnInit(): void {

    this.usuario = history.state.usuario;
    

    this.recetasService.obtenerTodos(null, null, this.usuario.idUsuarios, null).subscribe(recetas => {
      this.recetas = recetas;
    });
    
    let favoritosIds: number[]= this.usuario.favoritos.map(fav => fav.idRecetas);
   
    this.recetasService.obtenerFavoritos(favoritosIds).subscribe(recetasFavoritos=>{
  
      this.recetasFavoritos = recetasFavoritos;
  
    });
   
  }
  /**
   * Método para el botón mostrar más donde mostramos otras 4 recetas mñas cada vez que se pulsa el botón
   */
  mostrarMasRecetas() {
    this.numRecetasMostradas += 4;
    
  }

  /**
   * Método para el routing a la página de inicio mandando al Usuario
   */
  volverInicio(){
    this.router.navigate([''],{state:{usuario:this.usuario}});
  }
  
/**
   * Método para el routing a la página de editarPerfil mandando al Usuario
   */
  mandarUsuario(){  
    this.router.navigate(['/editarPerfil'],{state:{usuario:this.usuario}});
  }

  /**
   * Método para el routing a la página de mostrar una receta mandando al Usuario
   */
  mandarUsuarioReceta(recetaId: number){
    this.router.navigate(['/mostraUnaReceta'],{state:{usuario:this.usuario,recetaId:recetaId }});
  }

  /**
   * Método para el routing a la página añadir una receta
   */
  UsuarioAnyadeReceta(){
    this.router.navigate(['/subirReceta'],{state:{usuario:this.usuario}});
  }
}
