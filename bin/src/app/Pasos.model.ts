



/**
 * Clase que representa los pasos que añadimos a la recetas.
 */
export class Pasos{

    /**
     * Identificador único del paso.
     */
    public idPasos:number;

    /**
     * Identificador de la receta
     */
    public idRecetas:number;

    /**
     * Tipo de paso
     */
    public tipo: number;

    /**
     * Descripción del paso.
     */
    public descripcion : string;

    /**
     * Foto del paso
     */
    public foto: string;


 
  
    /**
     * Crea una instancia de la clase Pasos.
     * @param idPasos Identificador único del paso.
     * @param tipo Tipo de paso.
     * @param descripcion Descripción del paso.
     * @param foto Foto del paso.
     * @param idRecetas Identificador único de la receta a la que pertenece el paso
     */
    constructor(idPasos: number, idRecetas: number, tipo: number, descripcion: string, foto: string) {
        this.idPasos = idPasos;
        this.idRecetas = idRecetas;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.foto = foto;
    

}
}