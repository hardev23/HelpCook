package com.help.cook.helpcook.models;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


/**
 * Objeto para la devolución del objeto Recetas
 * @author Jennifer
 * @version 1.0, 2022/11/05
 *
 */
@Data
public class RecetasResponse {

    private Integer idRecetas;

    private Integer idUsuarios;

    private String descripcion;

    private Float tiempo;

    private String foto;

    private String titulo;

    private String categoria;

    private LocalDateTime fechaAlta;

    private Float valoracionMedia;

    private Integer comensales;

    private List<IngredientesResponse> ingredientesResponse;
    
    private List<PasosResponse> pasosResponse;

}
