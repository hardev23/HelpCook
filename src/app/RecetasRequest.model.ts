import { Ingredientes } from "./Ingredientes.model";
import { Pasos } from "./Pasos.model";

/**
 * Clase que representa la receta que vamos a enviar para guardarla.
 */
export class RecetasRequest{

    /**
     * Identificador único de la receta.
     */
    public idRecetas:number; 

    /**
     * Identificador del usuario que ha creado la receta.
     */
    public idUsuarios:number; 

    /**
     * Descripción de la receta.
     */
    public descripcion:string;

    /**
     * Tiempo estimado de preparación de la receta
     */
    public tiempo:number; 

    /**
     * URL de la foto de la receta.
     */
    public foto:string; 

    /**
     * Título de la receta.
     * 
     */
    public titulo:string; 

    /**
     * Categoría de la receta.
     */
    public categoria:string;

    /**
     * Fecha en la que se añadió la receta
     */
    public fechaAlta:Date;

    /**
     * Valoración media de la receta
     */
    public valoracionMedia:number;

    /**
     * Número de comensales para los que está diseñada la receta
     */
    public comensales:number;

    /**
     * Ingredientes necesarios para la receta.
     */
    public ingredientes: Ingredientes[];

    /**
     * Pasos necesarios para la elaboración de la receta.
     */
    public pasos: Pasos[];  

    
    

    /**
     * Crea una instancia de la clase Receta.
     * @param idRecetas Identificador único de la receta.
     * @param idUsuarios Identificador del usuario que ha creado la receta.
     * @param descripcion Descripción de la receta.
     * @param tiempo Tiempo estimado de preparación de la receta (en minutos).
     * @param foto URL de la foto de la receta.
     * @param titulo Título de la receta.
     * @param categoria Categoría de la receta.
     * @param fechaAlta Fecha de alta de la receta.
     * @param valoracionMedia Valoración media de la receta.
     * @param comensales Número de comensales para los que está diseñada la receta.
     * @param ingredientes Ingredientes necesarios para la receta.
     * @param pasos Pasos necesarios para la elaboración de la receta.
     */
    constructor(idRecetas: number, idUsuarios: number, descripcion: string, tiempo: number, foto: string, titulo: string, categoria: string, fechaAlta: Date, valoracionMedia: number, comensales: number, ingredientes: Ingredientes[], pasos: Pasos[]) {
        this.idRecetas = idRecetas;
        this.idUsuarios = idUsuarios;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        this.foto = foto;
        this.titulo = titulo;
        this.categoria = categoria;
        this.fechaAlta = fechaAlta;
        this.valoracionMedia = valoracionMedia;
        this.comensales = comensales;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
    }

}
