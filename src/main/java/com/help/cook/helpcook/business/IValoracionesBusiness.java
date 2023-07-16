package com.help.cook.helpcook.business;

import java.util.List;

import com.help.cook.helpcook.models.UsuariosResponse;
import com.help.cook.helpcook.models.ValoracionesRequest;
import com.help.cook.helpcook.models.ValoracionesResponse;


/**
 * Declarada interfaz Valoraciones en el paquete Business,
 * se definen los métodos que se usarán en la clase 
 * @author Jennifer
 * @version 1.0, 2022/11/05
 */
public interface IValoracionesBusiness {
	
	/**
	 * Método para crear valoraciones
	 * @param request. Recibimos la valoración con los datos dados por el usuario
	 * @return Devolvemos la valoración
	 */
	ValoracionesResponse crear(ValoracionesRequest request);
	
	
	/**
	 * Método para obtener una valoracion por su id
	 * @param id. Recibimos el id del ingrediente a obtener
	 * @return Devolvemos la valoración
	 */
	ValoracionesResponse obtener(Integer id);
	
	
	/**
	 * Método para borrar una valoración
	 * @param id. Recibimos el id de la Valaración a eliminar
	 */
	void eliminar(Integer id);
	
	
	/**
	 * Método para modificar los valores de una valoración
	 * @param request. Recibimos la Valoración con los nuevos datos
	 * @param id. Recibimos el id de la valoración a modificar
	 * @return Devolvemos la valoración con los nuevos datos dados por el usuario 
	 */
	ValoracionesResponse modificar(ValoracionesRequest request, Integer id);
	
	
	/**
	 * Método para obtener todas las valoraciones de la base de datos
	 * @return Devolvemos una Lista con todas las valoraciones de la base de datos
	 */
	List<ValoracionesResponse> obtenerTodos();

}
