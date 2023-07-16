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

import com.help.cook.helpcook.business.IRecetasBusiness;
import com.help.cook.helpcook.models.PasosResponse;
import com.help.cook.helpcook.models.RecetasRequest;
import com.help.cook.helpcook.models.RecetasResponse;


/**
 * Clase para la conexión con el Front 
 * @author Hugo
 *@version 1.0, 2022/11/05
 */
@RestController
@RequestMapping("recetas")
public class RecetasController {
	/**
	 * Injectamos la interface
	 */
	@Autowired
	IRecetasBusiness recetasBusiness;
	
	
	
	/**
	 * Método de comunicacion con el Front para crear una Receta
	 * @param request El usuario nos manda los valores para crear la receta
	 * @return Devolvemos la receta creada con los datos introducidos por el usuario que nos devuelve el método business 
	 */
	@PostMapping
	public RecetasResponse crear(@RequestBody RecetasRequest request) {
		
		return recetasBusiness.crear(request);
				
	}
	
	
	/**
	 * Método de comunicacion con el Front para obtener una receta
	 * @param id. Recibimos el id de la receta que el usuario quiere visualizar
	 * @return devolvemos la receta seleccionada que nos devuelve el método business 
	 */
	@GetMapping("/{id}")
	public RecetasResponse obtener(@PathVariable Integer id) {
		return recetasBusiness.obtener(id);
	}
	
	
	/**
	 * Método de comunicacion con el Front  para borrar una receta
	 * @param id. Recibimos el id de la receta a borrar
	 */
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		recetasBusiness.eliminar(id);
	}
	
	
	/**
	 * Método de comunicacion con el Front para modificar una receta
	 * @param request. Recibimos los nuevos valores del objeto receta
	 * @param id. Recibimos el id de la receta que se quiere modificar
	 * @return Devolvemos la receta modificada que nos devuelve el método business 
	 */
	@PutMapping ("/{id}")
	public RecetasResponse modificar(@RequestBody RecetasRequest request, @PathVariable Integer id) {
		return recetasBusiness.modificar(request, id);
	}
	
	
	/**
	 * Método para ontener una lista de Recetas según los parametros que necesitemos
	 * @param categoria. Recibimos la categoria de recetas que se quieren obtener o vacio
	 * @param idIngredientes. Recibimos los id de los ingredientes 
	 * @param idUsuario. Recibimos el Id del Usuario para mostrar las recetas que ha subido
	 * @param ordenacion. Recibimos el parametro mejor valoradas o mas recientes
	 * @return Devolvemos una lista de recetas según los parámetros introducidos que nos devuelve el método business 
	 */
	@GetMapping
	public List<RecetasResponse> obtenerTodos(@RequestParam(required = false) String categoria,@RequestParam(required = false) List<Integer> idIngredientes, @RequestParam(required = false) Integer idUsuario,@RequestParam(required = false) String ordenacion ) {
		return recetasBusiness.obtenerTodos(categoria,idIngredientes,idUsuario,ordenacion);
	}
	
	
	/**
	 * Método para obtener una lista de Recetas de Favoritos de un Usuario
	 * @param listaFavoritos
	 * @return devolvemos una lista de las Recetas que un Usuario tiene como favoritos
	 */
	@GetMapping("/recetasFavoritos")
	public List<RecetasResponse> obtenerFavoritos(@RequestParam(required = false) List<Integer> idRecetas) {
		return recetasBusiness.obtenerFavoritos(idRecetas);
	}
}
