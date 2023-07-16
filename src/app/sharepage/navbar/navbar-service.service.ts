import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ingredientes } from 'src/app/Ingredientes.model';


/**
 * Servicio que proporciona acceso a la API REST utilizando el HttpClient de Angular
 * 
 * Utilizado para la obtencion de los ingredientes del componente NavBar
 */
@Injectable({
  providedIn: 'root'
})
export class NavbarService {
  /**
  * Constructor del servicio que inyecta el HttpClient de Angular.
  * @param http. Cliente HTTP utilizado para realizar solicitudes a una API REST.
  */
  constructor(private http: HttpClient) {}

  /**
   * Método para la conexión con el método del backend para recibir ingredientes del back,
   * @param  {string} ingredientesAMostrar. Recibimos el valor para el filtro de buscar ingredientes en caso de necesitarlo
   * @returns {Ingredientes} Devolvemos el array con los ingredientes
   */
  obtenerIngredientes(ingredientesAMostrar: string) {
    let param = '';

    if (ingredientesAMostrar != null) {
      param += '?categoria=' + ingredientesAMostrar; 
    }

    return this.http.get<Ingredientes[]>('http://localhost:8081/ingredientes?' + param);
  }

}
