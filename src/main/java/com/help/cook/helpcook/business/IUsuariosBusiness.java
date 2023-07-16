package com.help.cook.helpcook.business;


import java.io.IOException;
import java.util.List;



import com.help.cook.helpcook.models.IngredientesResponse;
import com.help.cook.helpcook.models.UsuariosRequest;
import com.help.cook.helpcook.models.UsuariosResponse;
import com.help.cook.helpcook.repository.UsuariosRepository;
import com.help.cook.helpcook.repository.domain.Ingredientes;


/**
 * Declarada interfaz Usuarios en el paquete Business,
 * se definen los métodos que se usarán en la clase 
 * @author Hugo
 * @version 1.0, 2022/11/05
 */
public interface IUsuariosBusiness {
	
	/**
	 * Método para crear un Usuario,
	 * @param foto 
	 * @param request. Le mandamos el Usuario con los datos introducidos por éste
	 * @return Devolvemos el Usuario con los datos introducidos por éste
	 * @throws IOException 
	 */
	UsuariosResponse crear(UsuariosRequest request);
	
	/**
	 * Método para obtener un usuario,
	 * @param id. Recibimos el id del usuario
	 * @return Devolvemos el Usuario
	 */
	UsuariosResponse obtener(Integer id);
	
	/**
	 * Método para borrar un usuario
	 * @param id. Recibimos el id del usuario a borrar
	 */
	void eliminar(Integer id);
	
	
	/**
	 * Método para modificar un Usuario
	 * @param request. Recibimos el Usuario con los nuevos valores
	 * @param id. Recibimos el id del Usuario a modificar
	 * @return Devolvemos el Usuario con los nuevos datos dados por el usuario
	 */
	UsuariosResponse modificar(UsuariosRequest request, Integer id);
	
	/**
	 * Método para obtener todos los Usuarios de la base de datos,
	 * @return Devolvemos una lista con todos los Usuarios
	 */
	List<UsuariosResponse> obtenerTodos();

	
	/**
	 * Método para la validación (Login)
	 * @param email. Recibimos el email del Usuario
	 * @param contrasenia. Recibimos la contraseña del Usuario
	 * @return Devolvemos el Usuario que posea los valores de los parámetros que le mandamos
	 */
	UsuariosResponse validarUsuario(String email, String contrasenia);


	


}
