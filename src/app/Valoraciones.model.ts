import { Usuarios } from "./Usuarios.model";


/**
 * Clase que representa las valoraciones realizadas por los usuarios sobre las recetas
 */
export class Valoraciones{

    /**
     * Identificador único de la valoración.
     */
    public idValoraciones: number;

    /**
     * Identificador único de la receta valorada.
     */
    public idRecetas: number;

    /**
     * Valoración numérica de la receta.
     */
    public valor: number;



    /**
     * Crea una instancia de la clase Valoraciones.
     * @param idValoraciones Identificador único de la valoración.
     * @param idReceta Identificador único de la receta valorada.
     * @param valor Valoración numérica de la receta.
     */
    constructor(idValoraciones: number, idRecetas: number, valor: number) {
        this.idValoraciones = idValoraciones;
        this.idRecetas = idRecetas;
        this.valor = valor;
  
    }
}