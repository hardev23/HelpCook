package com.help.cook.helpcook.models;




import lombok.Data;


/**
 * Objeto para recibir datos del front
 * @author Hugo
 *@version 1.0, 2022/11/05
 */
@Data
public class UsuariosRequest {

	private Integer idUsuarios;

	private String nick;
	
	private String contrasenia;

	private String nombre;

	private String apellido;

	private String email;
	
	private String foto;
}
