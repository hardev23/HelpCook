package com.help.cook.helpcook.models;

import lombok.Data;


/**
 * Objeto para la devolución del objeto Valoraciones
 * @author Jennifer
 * @version 1.0, 2022/11/05
 *
 */
@Data
public class ValoracionesResponse {
	
	private Integer idValoraciones;
	
	private Integer idRecetas;
	
	private Integer valor;

}
