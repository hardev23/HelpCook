package com.help.cook.helpcook.models;

import lombok.Data;

//CONTIENE LOS DATOS DE COMUNICACIÓN CON EL FRONT QUE USAREMOS LUEGO EN EL PAQUETE CONTROLLER
//REQUEST ES PARA LA ENTRADA DE DATOS


/**
 * Objeto para recibir datos del front
 * @author Jennifer
 *@version 1.0, 2022/11/05
 */
@Data //Anotación para generar los getters & setters y constructor de la clase
public class IngredientesRequest {

	private Integer idIngredientes;
	
	private String nombre;
	
	private String tipo;

}
