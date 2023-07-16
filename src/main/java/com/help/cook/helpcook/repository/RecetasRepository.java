package com.help.cook.helpcook.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.help.cook.helpcook.repository.domain.Recetas;


/**
 * Generada Interfaz de la tabla Recetas para acceder a la base datos
 * @author Hugo
 *
 */
public interface RecetasRepository extends CrudRepository<Recetas, Integer>{
	
	
	
	
	List<Recetas> findAll();
	
	
	/**
	 * Declarada lista para almacenar las recetas que coincidan con los filtros que recibe
	 * @param categoria. Le mandamos parámetro para filtrar por categoria
	 * @param idIngredientes.  Le mandamos parámetro para filtrar por ingredientes
	 * @param idUsuario.  Le mandamos parámetro para filtrar por Usuario que hizo la receta
	 * @return Lista con las recetas según el filtro
	 */
	@Query("SELECT r FROM Recetas r LEFT JOIN r.ingredientes i  WHERE ((:categoria is null or r.categoria like %:categoria%) " +
			"AND (coalesce(:idIngredientes) is null or i.ingredientes.idIngredientes in (:idIngredientes)) " +
			"AND (:idUsuario is null or r.idUsuarios = :idUsuario ))")
	Set<Recetas> findAdvance (@Param("categoria") String categoria, @Param ("idIngredientes") List<Integer> idIngredientes, @Param("idUsuario") Integer idUsuario);

	
	
	/**
	 * Declarada Lista para almacenar las recetas mejor valoradas
	 * @return Devuelve una Lista de recetas ordenada por las que posean mayor valoraciónMedia
	 */
	@Query("SELECT r FROM Recetas r ORDER BY valoracionMedia DESC")
	Set<Recetas> findByValoradas();
	
	
	/**
	 * Declarada Lista para almacenar las recetas más recientes
	 * @return Devuelve una Lista de recetas ordenada por las últimas añadidas
	 */
	@Query("SELECT r FROM Recetas r ORDER BY fechaAlta DESC")
	Set<Recetas> findByFechaAlta();
	
	
	/**
	 * Declarada Lista para almacenar las Recetas de los favoritos que posee un Usuario
	 * @param idRecetas. Le mandamos la lista de los id de las recetas que queremos obtener
	 * @return Lista de recetas
	 */
	@Query("SELECT r FROM Recetas r WHERE ID_Recetas in :idRecetas")
	Set<Recetas> findByFavoritos(@Param("idRecetas") List<Integer> idRecetas);
}
