package com.help.cook.helpcook.business;

import com.help.cook.helpcook.models.*;
import com.help.cook.helpcook.repository.domain.Favoritos;
import com.help.cook.helpcook.repository.domain.Valoraciones;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.help.cook.helpcook.repository.UsuariosRepository;
import com.help.cook.helpcook.repository.domain.Usuarios;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;



/**
 * Clase con la lógica del Negocio Usuarios
 * @author Jennifer
 * @version 1.0, 2022/11/05
 * @see com.help.cook.helpcook.business
 */
@Service
public class UsuariosBusinessImpl implements IUsuariosBusiness {

    @Autowired
    private UsuariosRepository usuariosRepository;



    /**
	 * Método con la lógica para crear un Usuario,
	 * asignamos los valores introducidos por el usuario a un usuario nuevo, lo guardamos en el repositorio,
	 * asignamos los valores al objeto a devolver
	 */
    @Override
    public UsuariosResponse crear(UsuariosRequest request){

        Usuarios usuarios = new Usuarios();

        UsuariosResponse response = new UsuariosResponse();

     // Validar si el email ya existe
        if (usuariosRepository.existsByEmail(request.getEmail())) {
            response.setExito(false);
            return response;
        }else {
        	
        
        
        usuarios.setNick(request.getNick());
        usuarios.setContrasenia(request.getContrasenia());
        usuarios.setNombre(request.getNombre());
        usuarios.setApellido(request.getApellido());
        usuarios.setEmail(request.getEmail());
        usuarios.setFoto(request.getFoto());
        
        Usuarios datoGuardado = usuariosRepository.save(usuarios);

        response.setIdUsuarios(datoGuardado.getId());
        response.setNick(datoGuardado.getNick());
        response.setContrasenia(datoGuardado.getContrasenia());
        response.setNombre(datoGuardado.getNombre());
        response.setApellido(datoGuardado.getApellido());
        response.setEmail(datoGuardado.getEmail());
        response.setFoto(datoGuardado.getFoto());
        response.setExito(true);
      

        return response;
        }
    }



	/**
  	 * Método con la lógica para obtener un Usuario,
  	 * recuperamos del repositorio el Usuario mediante su id,
	 * asignamos al objeto a devolver los datos guardados en el repositorio
  	 */
    @Override
    public UsuariosResponse obtener(Integer id) {

        UsuariosResponse response = new UsuariosResponse();
        
        List<FavoritosResponse> favoritosResponseList = new ArrayList<>();
        
      

        Usuarios datoGuardado = usuariosRepository.findById(id).get();
        
        response.setIdUsuarios(datoGuardado.getId());
        response.setNick(datoGuardado.getNick());
        response.setContrasenia(datoGuardado.getContrasenia());
        response.setNombre(datoGuardado.getNombre());
        response.setApellido(datoGuardado.getApellido());
        response.setEmail(datoGuardado.getEmail());
        response.setFoto(datoGuardado.getFoto());

        for (Favoritos favoritos : datoGuardado.getFavoritos()) {
            FavoritosResponse favoritosResponse = new FavoritosResponse();
            favoritosResponse.setIdFavoritos(favoritos.getIdFavoritos());
            favoritosResponse.setIdRecetas(favoritos.getIdRecetas());
            favoritosResponse.setDescripcion(favoritos.getDescripcion());
            favoritosResponseList.add(favoritosResponse);
        }

        response.setFavoritos(favoritosResponseList);

   


        return response;
    }
    
    
    /**
  	 * Método con la lógica para validar el acceso de un Usuario,
  	 * recuperamos de la base de datos el Usuario que posea los valores quer manda el usuario,
  	 * asignamos esos valores al objeto a devolver
  	 */
    public UsuariosResponse validarUsuario(String email, String contrasenia) {
    	
    	UsuariosResponse response = new UsuariosResponse();
    	
    	 List<FavoritosResponse> favoritosResponseList = new ArrayList<>();
    
    	
    	Usuarios datoGuardado = usuariosRepository.findByEmailAndContrasenia(email,contrasenia);
    	System.out.println(datoGuardado);
    	  response.setIdUsuarios(datoGuardado.getId());
          response.setNick(datoGuardado.getNick());
          response.setContrasenia(datoGuardado.getContrasenia());
          response.setNombre(datoGuardado.getNombre());
          response.setApellido(datoGuardado.getApellido());
          response.setEmail(datoGuardado.getEmail());
          response.setFoto(datoGuardado.getFoto());

          for (Favoritos favoritos : datoGuardado.getFavoritos()) {
              FavoritosResponse favoritosResponse = new FavoritosResponse();
              favoritosResponse.setIdFavoritos(favoritos.getIdFavoritos());
              favoritosResponse.setIdRecetas(favoritos.getIdRecetas());
              favoritosResponse.setDescripcion(favoritos.getDescripcion());
              favoritosResponseList.add(favoritosResponse);
          }

          response.setFavoritos(favoritosResponseList);

      

       

		return response;
    }

    /**
  	 * Método con la lógica para eliminar un Usuario,
  	 * eliminamos el Usuario de la Base de datos
  	 */  
    public void eliminar(Integer id) {
        usuariosRepository.deleteById(id);

    }



    /**
  	 * Método con la lógica para modificar un Usuario,
  	 * recuperamos el Usuario de la base de datos, le asignamos los nuevos valores, lo guardamos en el repositorio,
  	 * asignamos al objeto a devolver los valores que se acaban de guardar
  	 */
    public UsuariosResponse modificar(UsuariosRequest request, Integer id) {

    	
        UsuariosResponse response = new UsuariosResponse();
        
        List<FavoritosResponse> favoritosResponseList = new ArrayList<>();

        Usuarios usuario = usuariosRepository.findById(id).get();

       
        usuario.setNick(request.getNick());
        usuario.setContrasenia(request.getContrasenia());
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setFoto(request.getFoto());

        
        for (Favoritos favoritos : usuario.getFavoritos()) {
            FavoritosResponse favoritosResponse = new FavoritosResponse();
            favoritosResponse.setIdFavoritos(favoritos.getIdFavoritos());
            favoritosResponse.setIdRecetas(favoritos.getIdRecetas());
            favoritosResponse.setIdUsuarios(usuario.getId());
            favoritosResponse.setDescripcion(favoritos.getDescripcion());
            favoritosResponseList.add(favoritosResponse);
        }

       
        
        Usuarios datoModificado = usuariosRepository.save(usuario);
        
  
        response.setIdUsuarios(datoModificado.getId());
        response.setNick(datoModificado.getNick());
        response.setContrasenia(datoModificado.getContrasenia());
        response.setNombre(datoModificado.getNombre());
        response.setApellido(datoModificado.getApellido());
        response.setEmail(datoModificado.getEmail());
        response.setFoto(datoModificado.getFoto());
        response.setExito(true);
        response.setFavoritos(favoritosResponseList);

        
        return response;
    }
	
  
    /**
  	 * Método con la lógica para obtener una lista con todos los Usuarios,
  	 * recuperamos los Usuarios de la base de datos en una lista,
	 * recorremos la lista recuperando cada usuario y asigando los valores del usuario recuperado al objeto a devolver,
	 * metemos en la lista a devolver los Usuarios 
  	 */
	public List<UsuariosResponse> obtenerTodos() {
		
		List<UsuariosResponse> usuariosResponseLista = new ArrayList<>();
		
		List<Usuarios> usuariosLista = usuariosRepository.findAll();
		
		for(Usuarios usuario: usuariosLista) {
			
			UsuariosResponse usuariosResponse = new UsuariosResponse();
			
			usuariosResponse.setIdUsuarios(usuario.getId());
			usuariosResponse.setContrasenia(usuario.getContrasenia());
			usuariosResponse.setNick(usuario.getNick());
			usuariosResponse.setNombre(usuario.getNombre());
			usuariosResponse.setApellido(usuario.getApellido());
			usuariosResponse.setEmail(usuario.getEmail());
			usuariosResponse.setFoto(usuario.getFoto());
			
			usuariosResponseLista.add(usuariosResponse);	
			
		}
		
		
	
		return usuariosResponseLista;
	}
	

}
