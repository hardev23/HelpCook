package com.help.cook.helpcook.repository.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;




/** 
 * Clase de la tabla Favoritos de BBDD Helpcook
 * @author Jennifer
 * @version 1.0, 2022/11/05 
 */
@Getter
@Setter
@Entity
public class Favoritos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFavoritos;
	
	private Integer idRecetas;
	
	private String descripcion;

	
	
	@ManyToOne
	@JoinColumn(name="ID_Usuarios")
	private Usuarios usuarios;



	
	
}
