package com.help.cook.helpcook.business;

import java.util.ArrayList;
import java.util.List;

import com.help.cook.helpcook.repository.domain.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.help.cook.helpcook.models.FavoritosRequest;
import com.help.cook.helpcook.models.FavoritosResponse;
import com.help.cook.helpcook.repository.FavoritosRepository;
import com.help.cook.helpcook.repository.domain.Favoritos;


/**
 * Clase con la lógica del Negocio Favoritos
 * @author Jennifer
 * @version 1.0, 2022/11/05
 * @see com.help.cook.helpcook.business
 */
@Service
public class FavoritosBusinessImpl implements IFavoritosBusiness {

	@Autowired
	private FavoritosRepository favoritosRepository;

	/**
	 * Método con la lógica para crear un Favorito,
	 * le asignamos a un nuevo Objeto Favorito los valores creados por el usuario, lo guardamos en el repositorio,
	 * asignamos los valores guardados al objeto a devolver
	 */
	@Override
	public FavoritosResponse crear(FavoritosRequest request) {

		Favoritos favoritos = new Favoritos();

		FavoritosResponse response = new FavoritosResponse();
		
		Usuarios usuarios = new Usuarios();

		favoritos.setIdRecetas(request.getIdRecetas());
		favoritos.setDescripcion(request.getDescripcion());

		usuarios.setId(request.getIdUsuarios());
		favoritos.setUsuarios(usuarios);

		Favoritos datoGuardado = favoritosRepository.save(favoritos);

		response.setIdFavoritos(datoGuardado.getIdFavoritos());
		response.setIdRecetas(datoGuardado.getIdRecetas());
		response.setIdUsuarios(datoGuardado.getUsuarios().getId());
		response.setDescripcion(datoGuardado.getDescripcion());

		return response;
	}

	
	/**
	 * Método con la lógica para recuperar un Favorito,
	 * recuperamos del repositorio el favorito por su id,
	 * asignamos al objeto a devolver los datos del Favorito que acabamos de recuperar
	 */
	@Override
	public FavoritosResponse obtener(Integer id) {

		FavoritosResponse response = new FavoritosResponse(); // Creamos el objeto que devolveremos al front

		Favoritos datoGuardado = favoritosRepository.findById(id).get(); // Recuperamos el objeto de la base de datos

		response.setIdFavoritos(datoGuardado.getIdFavoritos());
		response.setIdRecetas(datoGuardado.getIdRecetas());
		response.setIdUsuarios(datoGuardado.getUsuarios().getId());
		response.setDescripcion(datoGuardado.getDescripcion());

		return response;
	}
	

	/**
	 * Método con la lógica para eliminar un Favorito,
	 * borramos de la base de datos el favorito mediante su id
	 */
	@Override
	public void eliminar(Integer id) {

		favoritosRepository.deleteById(id);

	}
	
	
/**
 * Método con la lógica para modificar un Favorito,
 * recuperamos el favorito que queremos modificar, le asignamos los nuevos valores, y lo guardamos en el repositorio
 * asignamos al Favorito que vamos a devolver los nuevos valores
 */
	@Override
	public FavoritosResponse modificar(FavoritosRequest request, Integer id) {

		FavoritosResponse response = new FavoritosResponse();

		Favoritos datoGuardado = favoritosRepository.findById(id).get();

		datoGuardado.setIdRecetas(request.getIdRecetas());
		datoGuardado.getUsuarios().setId(request.getIdUsuarios());
		datoGuardado.setDescripcion(request.getDescripcion());

		Favoritos datoModificado = favoritosRepository.save(datoGuardado);

		response.setIdFavoritos(datoModificado.getIdFavoritos());
		response.setIdRecetas(datoModificado.getIdRecetas());
		response.setIdUsuarios(datoModificado.getUsuarios().getId());
		response.setDescripcion(datoModificado.getDescripcion());

		return response;
	}

	/**
	 * Método con la lógica para obtener una lista de Favoritos,
	 *  si el párametro que recibimos es nulo recibimos todos los Favoritos de la base de Datos,
	 *  si se recibe el IdUsuario mostrará los favoritos de ese usuario.
	 */
	@Override
	 public List <FavoritosResponse> obtenerTodos(Integer idUsuario) {
		 
		List<FavoritosResponse> favoritosResponseLista = new ArrayList<>(); //Creamos una lista que nos devolvera los favoritos a mostrar
			
		List<Favoritos> favoritosLista = favoritosRepository.findByUsuarios(idUsuario); //Creamos una lista que almacena todos los objetos de favoritos de la BBDD con ese Usuario
			
			
			//Recorremos la lista
			for(Favoritos favorito: favoritosLista) {
				FavoritosResponse favoritosResponse = new FavoritosResponse(); //Creamos el objeto favoritos a devolver
				
				//Le asignamos los datos que capturamos del objeto favorito de la tabla
				favoritosResponse.setIdFavoritos(favorito.getIdFavoritos());
				favoritosResponse.setIdRecetas(favorito.getIdRecetas());
				favoritosResponse.setDescripcion(favorito.getDescripcion());
				favoritosResponse.setIdUsuarios(favorito.getUsuarios().getId());
				
				
				
				//Metemos en la lista a devolver los objetos
				favoritosResponseLista.add(favoritosResponse);
			}
		 
		 return favoritosResponseLista;
	 }
	 
	

}
