package com.help.cook.helpcook.business;

import java.util.List;

import com.help.cook.helpcook.models.PasosRequest;
import com.help.cook.helpcook.models.PasosResponse;


/**
 * Declarada interfaz Pasos en el paquete Business,
 * se definen los métodos que se usarán en la clase 
 * @author Hugo
 * @version 1.0, 2022/11/05
 */
public interface IPasosBusiness {

	
	/**
	 * Método para crear un paso
	 * @param request. Se recibe el objeto del Front
	 * @return Devuelve el Paso creado
	 */
	PasosResponse crear(PasosRequest request);
	
	
	/**
	 * Método para recuperar los datos de un paso por su id,
	 * @param id. Recibimos el id del paso que se quiere obtener
	 * @return Devolvemos el Paso solicitado
	 */
	PasosResponse obtener(Integer id);
	
	/**
	 * Método para borrar un paso
	 * @param id. Recimos el id del Paso a borrar
	 */
	void eliminar(Integer id);
	
	
	/**
	 * Método para modificar los valores de un paso
	 * @param request. Recimos el objeto con los nuevos valores
	 * @param id. Recibimos el id del paso a modificar
	 * @return Devolvemos el Paso con los nuevos valores dados por el usuario
	 */
	PasosResponse modificar(PasosRequest request, Integer id);
	
	
	/**
	 * Método para obtener todos los pasos de la base de datos
	 * @return Devolvemos una Lista con todos los Pasos
	 */
	List<PasosResponse> obtenerTodos();

}
