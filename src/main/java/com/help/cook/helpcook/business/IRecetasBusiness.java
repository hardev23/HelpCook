package com.help.cook.helpcook.business;

import java.util.List;

import com.help.cook.helpcook.models.RecetasRequest;
import com.help.cook.helpcook.models.RecetasResponse;


/**
 * Declarada interfaz Recetas en el paquete Business,
 * se definen los métodos que se usarán en la clase 
 * @author Hugo
 * @version 1.0, 2022/11/05
 */
public interface IRecetasBusiness {
	
	/**
	 * Método para crear una Receta
	 * @param request. Recibimos la receta con los valores dados por el usuario.
	 * @return Devolvemos la Receta creada con los valores dados por el usuario
	 */
	RecetasResponse crear(RecetasRequest request);
	
	
	/**
	 * Método para recuperar los datos de una receta por su id,
	 * @param id. Recibimos el id de la receta que se quiere obtener
	 * @return obtenemos la receta solicitada
	 */
	RecetasResponse obtener(Integer id);
	
	
	/**
	 * Método para borrar una receta
	 * @param id. Recibimos el id de la Receta que se desea borrar
	 */
	void eliminar(Integer id);
	
	
	/**
	 * Método para modificar una receta
	 * @param request. Recibimos la Receta con los nuevos valores
	 * @param id. Recibimos el id de la receta que se va a modificar
	 * @return Devolvemos la Receta con los nuevos valores
	 */
	RecetasResponse modificar(RecetasRequest request, Integer id);
	
	
	/**
	 * Método para obtener todas las recetas de la base de datos,
	 * dependiendo de los parámetros que se reciban se mostrarán unas recetas u otras
	 * @param categoria. Recibimos la categoria de la Receta
	 * @param idIngredientes. Recibimos una lista con los ingredientes
	 * @param idUsuario. Recibimos el id del Usuario
	 * @param ordenacion. Recibimos el párametro de busqueda
	 * @return Devolvemos una lista con las recetas.
	 */
	List<RecetasResponse> obtenerTodos(String categoria, List<Integer> idIngredientes, Integer idUsuario, String ordenacion);
	
	
	/**
	 * Método para obtener las Recetas que un Usuario tiene como favoritos
	 * @param listaFavoritos
	 * @return Lista de Recetas de los favoritos
	 */
	List<RecetasResponse> obtenerFavoritos( List<Integer> idRecetas);
	

}
