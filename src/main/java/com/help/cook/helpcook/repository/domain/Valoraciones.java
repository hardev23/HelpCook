package com.help.cook.helpcook.repository.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;




/** 
 * Clase de la tabla Valoraciones de BBDD Helpcook
 * @author Jennifer
 * @version 1.0, 2022/11/05
 * 
 */
@Getter
@Setter
@Entity
public class Valoraciones {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idValoraciones;
	
	private Integer idRecetas;
	
	private Integer valor;

	
	
	

	

}
