package com.help.cook.helpcook.repository.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;




/** 
 * Clase de la tabla de relación entre ingredientes-recetas de BBDD Helpcook
 * @author Jennifer
 * @version 1.0, 2022/11/05
 */
@Getter
@Setter
@Entity
@Table(name =  "recetas_ingredientes") // Indicamos la tabla a la que hace referencia ya que tienen nombres distintos en backend y bd.
public class RecetasIngredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Rec_Ingr") // Indicamos la columna a la que hace referencia ya que tienen nombres distintos en backend y bd.
    private Integer id;
    
    private String cantidad;

    
    /**
     * Establecemos la relación entre las Tablas
     */
    @ManyToOne //Indicamos que es una relación de muchos a 1
    @JoinColumn(name = "ID_Recetas") //Le indicamos que use esa columna para la relación
    Recetas recetas;

   
    /**
     * Establecemos la relación entre las Tablas
     */
    @ManyToOne
    @JoinColumn(name = "ID_Ingredientes")
    Ingredientes ingredientes;

    



}
