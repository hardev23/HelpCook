import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingredientes } from '../../Ingredientes.model';
import { Receta } from 'src/app/Receta.model';
import { RecetasRequest } from 'src/app/RecetasRequest.model';
import { Valoraciones } from 'src/app/Valoraciones.model';
import { Favoritos } from 'src/app/Favoritos.model';


/**
 * Servicio que proporciona acceso a la API REST utilizando el HttpClient de Angular
 * 
 * Utilizado para las consultas de recetas.
 */
@Injectable()
export class recetasService {


  /**
   * Constructor del servicio que inyecta el HttpClient de Angular.
   * @param http. Cliente HTTP utilizado para realizar solicitudes a una API REST.
   */
  constructor(private http: HttpClient) {}

  /**
   * Método para la conexión con el método del backend para obtener Recetas
   * 
   * Según el párametro que recibamos modificamos la consulta que se hace al back asignando su correspondiente declaración 
   * 
   * @param {string} recetasAMostrar. Recibimos el valor para el filtro de mostrar recetas por categoría.
   * @param {number} idIngredientes. Recibimos el array con los Id de los ingredientes de la receta para el filtro por ingredientes. 
   * @param {number} idUsuario. Recibimos el id del Usuario para el filtro de las recetas subidas por el usuario.
   * @param {string} ordenacion. Recibimos el valor del párametro para el filtro de recetas según su ordenación.
   * @returns {Receta}  Devolvemos un array de las recetas obtenidas según lo requerido.
   */
  obtenerTodos(
    recetasAMostrar: string,
    idIngredientes: Ingredientes[],
    idUsuario: number, 
    ordenacion: string
  ): Observable<Receta[]> {
    let param = '';
 
    if (recetasAMostrar != null) {
      param += 'categoria=' + recetasAMostrar;
    }
    if (idIngredientes != null && param != '') {
      param += '&idIngredientes=' + idIngredientes.map(ingrediente => ingrediente.idIngredientes).join(',');
    } else if (idIngredientes != null && param == '') {
      param += 'idIngredientes=' + idIngredientes.map(ingrediente => ingrediente.idIngredientes).join(',');
    }

    if (idUsuario != null && param != '') {
      param += '&idUsuario=' + idUsuario;
    } else if(idUsuario != null && param == '') {
      param += 'idUsuario=' + idUsuario;
    }
    if (ordenacion != null && param != '') {
      param += '&ordenacion=' + ordenacion;
    } else if(ordenacion != null && param == '') {
      param += 'ordenacion=' + ordenacion;
    }
    
    return this.http.get<Receta[]>('http://localhost:8081/recetas?' + param);
  }


  /**
   * Método para la conexión con el método del backend para obtener Receta por su id,
   * 
   * Realizamos la petición http al método obtener del back pasandole el id de la receta
   * 
   * @param id. Recibimos el id de la receta que queremos mostrar.
   * @returns Devolvemos el objeto receta que recibimos del backend
   */
  obtener(id:number){
    
    return this.http.get<Receta>('http://localhost:8081/recetas/' + id);

  }


  /**
   * Método para la conexion con el front y obtener los favoritos de un Usuario
   * @param idRecetas. Recibimos un array con los id de las recetas que el usuario tiene como favoritos
   * @returns Devuelve una lista de las recetas con los ids que le hemos mandado
   */
  obtenerFavoritos(idRecetas: number[]): Observable<Receta[]> {

    let i:number = 0;
    let params = '';

    for(i;i<idRecetas.length;i++){
      if(i==0){
        params = 'idRecetas='+ idRecetas[0];
      }else{
        params += '&idRecetas='+idRecetas[i];
      }
      if(i === idRecetas.length - 1) {
      }
    }
    
    return this.http.get<any>('http://localhost:8081/recetas/recetasFavoritos?'+ params );
  }


  /**
   * Método para la conexión con el backend
   * para insertar una receta en la base de datos
   * @param receta. Recibimos la receta
   * @returns 
   */
  subirReceta(receta:RecetasRequest){

    return this.http.post('http://localhost:8081/recetas', receta);

  }

  /**
   * Método para la conexión con el backend y obtener  todos los ingredientes 
   * 
   * o los ingredientes según su tipo
   * @param tipo. Recibimos el tipo de los ingredientes que queremos obtener o la variable vacia para obtener todos
   * @returns Recibimos una lista de ingredientes
   */
  obtenerTodosIngredientes(tipo:string) {

    let param = '';
    if(tipo!=null){
      param = '?tipo='+ tipo;
    }
    
    return this.http.get<Ingredientes[]>('http://localhost:8081/ingredientes'+param);
}

/**
 * Método para la conexión con el back para añadir una valoración a la receta
 * @param valoracion. Recibimos el objeto valoracion con la valoracion hecha por el usuario 
 * @returns nos devuelve la valoración
 */
anyadirValoracion(valoracion:Valoraciones){

  return this.http.post('http://localhost:8081/valoraciones', valoracion);
}

/**
 * Método para la conexion con el back para añadir una receta como favorito a un Uusario
 * @param favorito Recibimos los datos del Favorito con la receta y el usuario que la guarda
 * @returns 
 */
agregarFavorito(favorito: Favoritos){

  console.log(favorito);
  return this.http.post('http://localhost:8081/favoritos', favorito);
}
}
