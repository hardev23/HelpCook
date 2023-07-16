package com.help.cook.helpcook.models;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


/**
 * Objeto para recibir datos del front
 * @author Hugo
 *@version 1.0, 2022/11/05
 */
@Data
public class RecetasRequest {
	
	private Integer idRecetas;
	
	private Integer idUsuarios;
	
	private String descripcion;
	
	private Float tiempo;
	
	private String foto;
	
	private String titulo;
	
	private String categoria;

	@DateTimeFormat(pattern = "dd/MM/yyyy'T'HH:mm:ss")
	private LocalDateTime fechaAlta;
	
	private Float valoracionMedia;
	
	private Integer comensales;

	private List<RecetasIngredientesRequest> ingredientes;
	
	
	private List<RecetasPasosRequest> pasos;

}
