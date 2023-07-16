import { Favoritos } from "./Favoritos.model";




/**
 * Clase que representa a los usuarios del sistema.
 */
export class Usuarios{

    /**
     * Identificador único del usuario.
     */
    public idUsuarios: number;

    /**
     * Nickname del usuario.
     */
    public nick : string;

    /**
     * Contraseña del usuario.
     */
    public contrasenia: string;

    /**
     * Nombre del usuario.
     */
    public nombre : string;

    /**
     * Apellido del usuario.
     */
    public apellido: string;

    /**
     * Dirección de correo electrónico del usuario.
     */
    public email: string;

    /**
     * URL de la foto de perfil del usuario.
     */
    public foto: string;

    /**
     * Lista de favoritos guardadas por el usuario 
     */
    public favoritos: Favoritos[];
 


     /**
     * Crea una instancia de la clase Usuarios.
     * @param idUsuario Identificador único del usuario.
     * @param nick Nickname del usuario.
     * @param contrasenia Contraseña del usuario.
     * @param nombre Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param email Dirección de correo electrónico del usuario.
     * @param foto URL de la foto de perfil del usuario.
     * @param receta Lista de recetas creadas por el usuario.
     */
     constructor(nick: string, contrasenia: string, nombre: string, apellido: string, email: string, foto: string, favoritos?: Favoritos[], idUsuarios?: number) {
        this.idUsuarios = idUsuarios;
        this.nick = nick;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.foto = foto;
        this.favoritos = favoritos;
    }
}