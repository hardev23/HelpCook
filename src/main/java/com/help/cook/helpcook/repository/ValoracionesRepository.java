package com.help.cook.helpcook.repository;

import org.springframework.data.repository.CrudRepository;

import com.help.cook.helpcook.repository.domain.Valoraciones;

import java.util.List;

/**
 * Generada Interfaz de la tabla Valoraciones para acceder a la base datos
 * @author Hugo
 * @version 1.0, 2022/11/05
 */
public interface ValoracionesRepository extends CrudRepository<Valoraciones, Integer>{

    List<Valoraciones> findAll();
    
    /**
     * Declarada Lista que recuperará todas valoraciones que posea esa receta
     * @param idReceta. Recibimos el id de la Receta 
     * @return Devuelve todas las valoraciones que posee una receta
     */
    List<Valoraciones> findByIdRecetas(Integer idReceta);
}
