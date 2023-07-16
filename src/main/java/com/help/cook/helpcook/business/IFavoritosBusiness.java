package com.help.cook.helpcook.business;

import java.util.List;

import com.help.cook.helpcook.models.FavoritosRequest;
import com.help.cook.helpcook.models.FavoritosResponse;
import com.help.cook.helpcook.models.IngredientesRequest;
import com.help.cook.helpcook.models.IngredientesResponse;
import com.help.cook.helpcook.repository.domain.Usuarios;

/**
 * Declarada interfaz Favoritos en el paquete Business,
 * se definen los métodos que se usarán en la clase 
 * @author Jennifer
 * @version 1.0, 2022/11/05
 */
public interface IFavoritosBusiness {
	
/**
 * Método para crear un favorito
 * @param request. Recibimos el objeto del Front
 * @return Devolvemos el Favorito creado
 */
	FavoritosResponse crear(FavoritosRequest request);
	
	
/**
 * Método para recuperar los datos de un favorito por su id
 * @param id. Recibimos el id del Favorito que queremos recuperar
 * @return Devolvemos el favorito
 */
	FavoritosResponse obtener(Integer id);
	
	
/**
 * Método para borrar un favorito
 * @param id. Recibimos el identificador del favorito a borrar.
 */
	void eliminar(Integer id);

	
	/**
	 * Método para modificar un favorito
	 * @param request. Recibimos el objeto favorito con los nuevos valores
	 * @param id. Recibimos el id del Favorito a modificar
	 * @return Devolvemos el objeto Favorito ya modificado
	 */
	FavoritosResponse modificar(FavoritosRequest request, Integer id);
	
	
	/**
	 * Método para obtener todos los favoritos,
	 *  según el parametro que le mande se obtienen todos los favoritos de la BBDD o sólo los favoritos de un Usuario
	 * @param IdUsuario.Recibimos el id del Usuario.
	 * @return Devolvemos una Lista con los favoritos 
	 */
	List<FavoritosResponse> obtenerTodos(Integer IdUsuario);

	

}
