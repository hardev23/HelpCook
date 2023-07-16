import { Component, OnInit } from '@angular/core';
import { Usuarios } from 'src/app/Usuarios.model';
import { RegistroService } from '../registro/registro-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { UsuariosResponse } from 'src/app/UsuariosResponse.model';


/**
 * Componente para la página de editar perfil
 *
 * Modificará al usuario con los valores introducidos en el form
 */
@Component({
  selector: 'app-editar-perfil',
  templateUrl: './editar-perfil.component.html',
  styleUrls: ['./editar-perfil.component.css']
})
export class EditarPerfilComponent implements OnInit{
  
  /**
   * Objeto Usuario que recibimos y modificaremos
   */
  usuario: Usuarios;

  /**
   * Variable para almacenar el nombre del usuario
   */
  nombre: string;

  /**
   * Variable para almacenar el apellido del Usuario
   */
  apellido: string;

  /**
   * Variable para almacenar el nick del Usuario
   */
  nick: string;
  
  /**
   * Variable para almacenar el email del Usuario
   */
  email: string;

  /**
   *  Variable para almacenar la contraseña del Usuario
   */
  contrasenia: string;

   /**
   *  Variable para almacenar la contraseñaRepetida del Usuario
   */
  contraseniaRep: string;

   /**
   *  Variable para almacenar la foto del Usuario
   */
  foto: string;

   /**
   *  Variable para  mostrar un mensaje de error
   */
  mensaje: string;
  

  /**
 * Variable para controlar que la petición de las contraseña coincidan
 */
  contraseniaCoincide: boolean = true;


  /**
   *  Injectamos los servicios 
   * 
   * almacenamos el usuario recibido
   * @param registSvc Servicio para hacer la consulta al backend
   * @param router Para el envio del Usuario a otras páginas
   */
  constructor(private registSvc: RegistroService, private router: Router) {
    
    this.usuario = history.state.usuario;
    
  }

  /**
   * Método que se carga cuando se inicializa la página
   */
  ngOnInit(): void {}



  /**
   * Método para la conversión de la foto a Base64 y almacenarla en la variable foto del usuario
   * @param event Recibimos el evento con el File de la foto
   */
  onFileSelected(event) {
    const file = event.target.files[0];

    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
      this.usuario.foto = reader.result as string;
    };
  }
  }
  
  /**
   * Método para la actualización de los datos del Usuario
   * 
   * Creamos un Usuario con los datos modficados y los mandamos al servicio para su inserción en la base de datos
   */
  updateUserService(){
    
    let usuario = new Usuarios(this.usuario.nick, this.usuario.contrasenia, this.usuario.nombre, this.usuario.apellido, this.usuario.email, this.usuario.foto);
     
    console.log("Usuario que se manda" + usuario);
  
      this.registSvc.updateUser(usuario,this.usuario.idUsuarios).subscribe((usuario) => {
               
          alert("Usuario modificado correctamente, " + usuario.nombre);
          this.router.navigate(['/perfil'],{state:{usuario:usuario}});
      },
        (error: HttpErrorResponse) => {
           console.log("Error en la respuesta del servidor:", error);
          if (error.error instanceof ErrorEvent) {
            this.mensaje = 'Error de red:'+ error.error.message;
          } else {
            this.mensaje = `Error en el servidor: ${error.status}: ${error.error}`;
          }
        }
    );
   
    };

}
