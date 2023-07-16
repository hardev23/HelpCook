import { Usuarios } from "./Usuarios.model";

/**
 * Clase que representa los favoritos de los usuarios.
 */
export class Favoritos{

    /**
     * Identificador único del favorito.
     */
    public idFavorito: number;

    /**
     * Identificador único de la receta favorita.
     */
    public idRecetas: number;
    
     /**
     * Usuarios que han agregado el favorito.
     */
     public idUsuarios: number;

    /**
     * Descripción del favorito 
     */
    public descripcion: string;


   


     /**
     * Crea una instancia de la clase Favoritos.
     * @param idFavorito Identificador único del favorito.
     * @param idReceta Identificador único de la receta favorita.
     * @param descripcion Descripción del favorito (opcional).
     * @param idUsuarios Identificador único del usuario que ha guardado la receta
     */
    constructor(idFavorito: number, idReceta: number, descripcion: string, idUsuarios: number) {
        this.idFavorito = idFavorito;
        this.idRecetas = idReceta;
        this.idUsuarios = idUsuarios;
        this.descripcion = descripcion;
        
}
}