package com.help.cook.helpcook.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.help.cook.helpcook.repository.domain.RecetasIngredientes;


//Es la interface que accede a la base de datos
//Extiende del CrudRepository y le indicamos la tabla y el tipo de la primaryKey
/**
 * Generada Interfaz de la tabla de relacion recetas_ingredientes para acceder a la base de datos
 * @author Jennifer
 * @version 1.0, 2022/11/05
 */
public interface  RecetasIngredientesRepository  extends CrudRepository<RecetasIngredientes, Integer> {
	
	List<RecetasIngredientes> findAll(); //Declaramos el método que usará Business


}
