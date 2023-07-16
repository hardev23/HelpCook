package com.help.cook.helpcook.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.help.cook.helpcook.business.IUsuariosBusiness;
import com.help.cook.helpcook.models.IngredientesRequest;
import com.help.cook.helpcook.models.IngredientesResponse;
import com.help.cook.helpcook.models.RecetasResponse;
import com.help.cook.helpcook.models.UsuariosRequest;
import com.help.cook.helpcook.models.UsuariosResponse;




/**
 * Clase para la conexión con el Front 
 * @author Hugo
 *@version 1.0, 2022/11/05
 */
@RestController
@RequestMapping("usuarios")
public class UsuariosController {
	
	/**
	 * Injectamos la interface
	 */
	@Autowired
	IUsuariosBusiness usuariosBusiness;
	
	
	
	/**
	 * Método de comunicacion con el Front para crear un usuario
	 * @param request. El usuario nos manda los valores para crear el usuario
	 * @return devolvemos el usuario creado con los datos introducidos por el usuario que nos devuelve el método business 
	 */
	@PostMapping
	public UsuariosResponse crear(@RequestBody UsuariosRequest request) {
		
		return usuariosBusiness.crear(request);
		
	}
	

	/**
	 * Método de comunicacion con el Front para obtener un usuario
	 * @param id. Recibimos el id del usuario que el usuario quiere visualizar
	 * @return devolvemos el usuario seleccionado que nos devuelve el método business 
	 */
	@GetMapping("/{id}")
	public UsuariosResponse obtener(@PathVariable Integer id) {
		return usuariosBusiness.obtener(id);
	}

	
	
/**
 * Método de comunicacion con el Front para el acceso del usuario
 * @param email. Recibimos el email que debe tener
 * @param contrasenia. Recibimos la contraseña que debe tener
 * @return devolvemos el usuario que posea esos valores del métododo, Buisiness
 */
	@GetMapping("/login")
	public UsuariosResponse validarUsuario(@RequestParam String email ,@RequestParam String contrasenia) {
		return usuariosBusiness.validarUsuario(email,contrasenia);
	}

	

	/**
	 * Método de comunicacion con el Front  para borrar un usuario
	 * @param id. Recibimos el id del usuario a borrar
	 */
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		usuariosBusiness.eliminar(id);
		
	}

	
	
	/**
	 * Método de comunicacion con el Front para modificar un usuario
	 * @param request. Recibimos los nuevos valores del objeto usuario
	 * @param id. Recibimos el id del usuario que se quiere modificar
	 * @return Devolvemos el usuario modificado que nos devuelve el método business 
	 */
	@PutMapping("/{id}")
	public UsuariosResponse modificar(@RequestBody  UsuariosRequest request, @PathVariable Integer id) {
		
		return usuariosBusiness.modificar(request,id);
		
	}
	

	
	/**
	 * Método de comunicacion con el Front  para recuperar todos los usuarios 
	 * @return devolvemos una lista de usuarios que nos devuelve el método business 
	 */
	@GetMapping
	public List<UsuariosResponse> obtenerTodos(){
		return usuariosBusiness.obtenerTodos();
	}
	

}
