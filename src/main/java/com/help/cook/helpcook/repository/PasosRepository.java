package com.help.cook.helpcook.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.help.cook.helpcook.repository.domain.Pasos;
/**
 * Generada Interfaz de la tabla Pasos para acceder a la base de datos
 * @author Hugo
 * @version 1.0, 2022/11/05
 */
public interface PasosRepository  extends CrudRepository<Pasos, Integer> {
	
	List<Pasos> findAll();

}
