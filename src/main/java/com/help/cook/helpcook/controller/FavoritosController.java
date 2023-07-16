package com.help.cook.helpcook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.help.cook.helpcook.business.IFavoritosBusiness;
import com.help.cook.helpcook.business.IIngredientesBusiness;
import com.help.cook.helpcook.models.FavoritosRequest;
import com.help.cook.helpcook.models.FavoritosResponse;
import com.help.cook.helpcook.models.IngredientesRequest;
import com.help.cook.helpcook.models.IngredientesResponse;
import com.help.cook.helpcook.repository.domain.Usuarios;


/**
 * Clase para la conexión con el Front 
 * @author jenni
 *@version 1.0, 2022/11/05
 */
@RestController
@RequestMapping("favoritos")
public class FavoritosController {
	/**
	 * Injectamos la interface
	 */
	@Autowired
	IFavoritosBusiness favoritosBusiness;
	
	

	/**
	 * Método de comunicacion con el Front para crear un favorito
	 * @param request. El usuario nos manda los valores para crear el favorito
	 * @return devolvemos el favorito creado con los datos introducidos por el usuario que nos devuelve el método business 
	 */
	@PostMapping
	public FavoritosResponse crear(@RequestBody  FavoritosRequest request) {
		
		return favoritosBusiness.crear(request);
		
	}
	
	
	
	/**
	 * Método de comunicacion con el Front para obtener un favorito
	 * @param id. Recibimos el id del favorito que el usuario quiere visualizar
	 * @return devolvemos el favorito seleccionado que nos devuelve el método business 
	 */
	@GetMapping("/{id}")
	public FavoritosResponse obtener(@PathVariable Integer id) {
		
		
		return favoritosBusiness.obtener(id);
	}

	
	
	/**
	 * Método de comunicacion con el Front  para borrar un favorito
	 * @param id. Recibimos el id del favorito a borrar
	 */
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		favoritosBusiness.eliminar(id);
		
	}

	
	
	/**
	 * Método de comunicacion con el Front para modificar un favorito
	 * @param request. Recibimos los nuevos valores del objeto favorito
	 * @param id. Recibimos el id del favorito que se quiere modificar
	 * @return Devolvemos el favorito modificado que nos devuelve el método business 
	 */
	@PutMapping("/{id}")
	public FavoritosResponse modificar(@RequestBody  FavoritosRequest request, @PathVariable Integer id) {
		
		return favoritosBusiness.modificar(request,id);
		
	}
	
	
/**
 * Método de comunicacion con el Front  para recuperar todos los favoritos de un usuario
 * @param idUsuario. Le pasamos el Id del Usuario 
 * @return devolvemos la lista de favoritos del usuario que nos devuelve el método business 
 */
	@GetMapping // Para obtener todo el listado 
	public List<FavoritosResponse> obtenerTodos(@RequestParam(required = false) Integer idUsuario) {


		return favoritosBusiness.obtenerTodos(idUsuario);
	}

}
