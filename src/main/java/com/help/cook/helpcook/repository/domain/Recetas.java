package com.help.cook.helpcook.repository.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;


import lombok.Data;




/** 
 * Clase de la tabla Recetas de BBDD Helpcook
 * @author Hugo
 * @version 1.0, 2022/11/05 
 */
@Data
@Entity
public class Recetas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRecetas;
	
	private Integer idUsuarios;
	
	private String descripcion;
	
	private Float tiempo;
	
	private String foto;
	
	private String titulo;
	
	private String categoria;
	
	private Timestamp fechaAlta;

	private Float valoracionMedia;
	
	private Integer comensales;

	
	/**
	 * Lista para almacenar los ingredientes que posee la receta a través de la tabla de relación RecetasIngredientes
	 */
	//Creamos el objeto para la relación con la tabla RecetasIngredientes
	@OneToMany(mappedBy = "recetas") //Le indicamos que es una relación de 1 a muchos, y que coja el objeto recetas de la tabla RecetasIngredientes
	Set<RecetasIngredientes> ingredientes;
	
	
	/**
	 * Lista para almacenar los pasos que posee la receta
	 */
	// Creamos la relación con la tabla Pasos
	@OneToMany(mappedBy = "recetas")
	Set<Pasos> pasos;

}
