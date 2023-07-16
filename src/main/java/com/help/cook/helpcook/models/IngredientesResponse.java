package com.help.cook.helpcook.models;

import java.util.List;

import com.help.cook.helpcook.repository.domain.Ingredientes;

import lombok.Data;

//CONTIENE LOS DATOS DE COMUNICACIÓN CON EL FRONT QUE USAREMOS LUEGO EN EL PAQUETE CONTROLLER
//REQUEST ES PARA LA SALIDA DE DATOS

/**
 * Objeto para la devolución del objeto Ingredientes
 * 
 * @author Jennifer
 * @version 1.0, 2022/11/05
 */
@Data // Anotación para generar los getters & setters y constructor de la clase
public class IngredientesResponse {

	private Integer idIngredientes;

	private String nombre;

	private String tipo;

	private String cantidad;

	private List<IngredientesResponse> ingredientesResponseLista;

	private List<Ingredientes> ingredientesLista;

}
