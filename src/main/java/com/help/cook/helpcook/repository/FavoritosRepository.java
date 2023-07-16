package com.help.cook.helpcook.repository;

import com.help.cook.helpcook.repository.domain.Favoritos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Generada Interfaz de la tabla Favoritos para acceder a la base de datos
 * @author Jennifer
 * @version 1.0, 2022/11/05
 */
public interface FavoritosRepository extends CrudRepository<Favoritos, Integer>{
	
	
/**
 * Declarada Lista para almacenar los Favoritos que posea un Usuario
 * @param IdUsuarios. Id del Usuario
 * @return Lista de los favoritos que posee un Usuario
 */
	@Query ("SELECT f FROM Favoritos f  LEFT JOIN f.usuarios u WHERE (:IdUsuarios is null or u.id = :IdUsuarios)")
	List<Favoritos> findByUsuarios(@Param("IdUsuarios") Integer IdUsuarios);
	
	

}
