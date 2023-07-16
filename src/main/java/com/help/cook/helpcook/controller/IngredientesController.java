package com.help.cook.helpcook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.help.cook.helpcook.business.IIngredientesBusiness;
import com.help.cook.helpcook.models.IngredientesRequest;
import com.help.cook.helpcook.models.IngredientesResponse;




/**
 * Clase para la conexión con el Front 
 * @author Jennifer
 *@version 1.0, 2022/11/05
 */
@RestController //Indica que es un controlador de tipo rest
@RequestMapping("ingredientes") //Le indica la ruta donde va a llamar el front
public class IngredientesController {
	/**
	 * Injectamos la interface
	 */
	@Autowired //Le indicamos que recupera la información del contexto de Spring de ésta Interface
	IIngredientesBusiness ingredientesBusiness;


	
	/**
	 * Método de comunicacion con el Front para crear un ingrediente
	 * @param request. El usuario nos manda los valores para crear el ingrediente
	 * @return devolvemos el Ingrediente creado con los datos introducidos por el usuario que nos devuelve el método business 
	 */
	@PostMapping //Para crear
	public IngredientesResponse crear(@RequestBody  IngredientesRequest request) { //Le indicamos que coja el cuerpo del mensaje del objeto de entrada

		return ingredientesBusiness.crear(request); //Llamamos al método pasandole el objeto de entrada

	}

	
	
	/**
	 * Método de comunicacion con el Front para obtener un ingrediente
	 * @param id. Recibimos el id del ingrediente que el usuario quiere visualizar
	 * @return devolvemos el ingrediente seleccionado que nos devuelve el método business 
	 */
	@GetMapping("/{id}") //Para recuperar, le indicamos que recupere el objeto por su id
	public IngredientesResponse obtener(@PathVariable Integer id) {


		return ingredientesBusiness.obtener(id);//Llamamos al método pasandole el id del objeto
	}

	
	

	
	/**
	 * Método de comunicacion con el Front  para borrar un ingrediente
	 * @param id. Recibimos el id del ingrediente a borrar
	 */
	@DeleteMapping("/{id}") //Para borrar
	public void eliminar(@PathVariable Integer id) { //@PathVariable indica que en la url va una variable
		ingredientesBusiness.eliminar(id);
	}


	
	/**
	 * Método de comunicacion con el Front para modificar un ingrediente
	 * @param request. Recibimos los nuevos valores del objeto ingredientes
	 * @param id. Recibimos el id del ingrediente que se quiere modificar
	 * @return Devolvemos el ingrediente modificado que nos devuelve el método business 
	 */
	@PutMapping("/{id}") //Para modificar
	public IngredientesResponse modificar(@RequestBody  IngredientesRequest request, @PathVariable Integer id) {

		return ingredientesBusiness.modificar(request,id); //Llamamos al método pasandole el objeto de entrada y el id

	}
	
	
	/**
	 * Método de comunicacion con el Front  para recuperar todos los ingredientes o los ingredientes según su tipo
	 * @param tipo. Le pasamos el tipo del ingrediente o vacio
	 * @return devolvemos una lista de ingredientes que nos devuelve el método business 
	 */
	@GetMapping // Para obtener todo el listado 
	public List<IngredientesResponse> obtenerTodos(@RequestParam(required = false) String tipo) {


		return ingredientesBusiness.obtenerTodos(tipo);
	}
	
}
