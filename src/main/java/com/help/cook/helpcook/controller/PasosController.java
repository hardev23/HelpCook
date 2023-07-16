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
import org.springframework.web.bind.annotation.RestController;

import com.help.cook.helpcook.business.IPasosBusiness;
import com.help.cook.helpcook.models.PasosRequest;
import com.help.cook.helpcook.models.PasosResponse;


/**
 * Clase para la conexión con el Front 
 * @author jenni
 *@version 1.0, 2022/11/05
 */
@RestController
@RequestMapping("pasos")
public class PasosController {
	/**
	 * Injectamos la interface
	 */
	@Autowired
	IPasosBusiness pasosBusiness;
	

	/**
	 * Método de comunicacion con el Front para crear un paso
	 * @param request. El usuario nos manda los valores para crear el paso
	 * @return devolvemos el paso creado con los datos introducidos por el usuario que nos devuelve el método business 
	 */
	@PostMapping
	public PasosResponse crear( @RequestBody PasosRequest request) {
		return pasosBusiness.crear(request);
	}
	
	/**
	 * Método de comunicacion con el Front para obtener un paso
	 * @param id. Recibimos el id del paso que el usuario quiere visualizar
	 * @return devolvemos el paso seleccionado que nos devuelve el método business 
	 */
	@GetMapping("/{id}")
	public PasosResponse obtener(@PathVariable Integer id) {
		return pasosBusiness.obtener(id);
	}
	
	
	/**
	 * Método de comunicacion con el Front  para borrar un paso
	 * @param id. Recibimos el id del paso a borrar
	 */
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		pasosBusiness.eliminar(id);
	}
	
	
	/**
	 * Método de comunicacion con el Front para modificar un paso
	 * @param request. Recibimos los nuevos valores del objeto pasos
	 * @param id. Recibimos el id del paso que se quiere modificar
	 * @return Devolvemos el paso modificado que nos devuelve el método business 
	 */
	@PutMapping("/{id}")
	public PasosResponse modificar(@RequestBody PasosRequest request, @PathVariable Integer id) {
		return pasosBusiness.modificar(request, id);
	}
	
	
	/**
	 * Método de comunicacion con el Front para recuperar todos los pasos
	 * @return Devolvemos una lista con todos los pasos que nos devuelve el método business 
	 */
	@GetMapping
	public List<PasosResponse> obtenerTodos() {
		return pasosBusiness.obtenerTodos();
	}
	

}
