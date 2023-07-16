package com.help.cook.helpcook.repository.domain;

import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;




/** 
 * Clase de la tabla Usuarios de BBDD Helpcook
 * @author Hugo
 * @version 1.0, 2022/11/05
 */

@Getter
@Setter
@Entity
public class Usuarios {

	@Id
	@Column(name = "ID_Usuarios")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nick;
	
	private String contrasenia;

	private String nombre;

	private String apellido;

	private String email;

	private String foto;
	
	
	

	
	@OneToMany(mappedBy = "usuarios") // Le indicamos que es una relación de 1 a muchos, y que coja el objeto usuarios de la tabla favoritos
	private  Set<Favoritos> favoritos;


}
