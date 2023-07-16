package com.help.cook.helpcook.models;

import lombok.Data;

import java.util.List;




/**
 * Objeto para la devolución del objeto Usuario
 * @author Jennifer
 * @version 1.0, 2022/11/05
 *
 */
@Data
public class UsuariosResponse {

    private Integer idUsuarios;

    private String nick;

    private String contrasenia;

    private String nombre;

    private String apellido;

    private String email;

    private String foto;
    
    private List<FavoritosResponse> favoritos;

    private boolean exito;


}
