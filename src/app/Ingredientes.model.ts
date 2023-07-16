import { Receta } from "./Receta.model";


/**
 * Clase que representa los ingredientes de las recetas.
 */
export class Ingredientes{

    /**
     * Identificador único del ingrediente.
     */
    public idIngredientes : number;

    /**
     * Nombre del ingrediente.
     */
    public nombre: string;

    /**
     * Tipo de ingrediente
     */
    public tipo: string;
    /**
     * Variable para asignar la cantidad de cada ingrediente
     */
    public cantidad: string;


    /**
     * Recetas en las que se utiliza este ingrediente.
     */
    public recetas: Receta[];

    /**
     * Variable para saber si se selecciona el objeto
     */
    public seleccionado: boolean;

    /**
     * Crea una instancia de la clase Ingredientes.
     * @param idIngredientes Identificador único del ingrediente.
     * @param nombre Nombre del ingrediente.
     * @param tipo Tipo de ingrediente (opcional).
     * @param recetas Recetas en las que se utiliza este ingrediente.
     * @param seleccionado Si ha sido seleccionado el ingrediente o no en el menu
     */
    constructor(idIngredientes: number, nombre: string, tipo: string, cantidad: string, recetas: Receta[], seleccionado: boolean) {
        this.idIngredientes = idIngredientes;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.recetas = recetas;
        this.seleccionado = false;
     
}
}