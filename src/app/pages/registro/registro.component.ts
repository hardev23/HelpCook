import { Component, OnInit } from '@angular/core';
import { RegistroService } from './registro-service.service';
import { Usuarios } from 'src/app/Usuarios.model';
import { Router } from '@angular/router';
import { UsuariosResponse } from 'src/app/UsuariosResponse.model';
/**
 * Componente para la página de registro
 *
 * Creará el usuario que se ha creado con los valores introducidos en el form
 */
@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
})
export class RegistroComponent implements OnInit {
  /**
   * Variable para almacenar el nombre real de la persona que se registra
   */
  nombre: string;

  /**
   * Variable para almacenar los apellidos de la persona que se registra
   */
  apellido: string;

  /**
   * Variable para almacenar el nick/username que tendrá en su cuenta de usuario
   */
  nick: string;

  /**
   * Variable para almacenar el email con el que se registra la persona y que estara asignado a su cuenta de usuario
   */
  email: string;

  /**
   * Variable para almacenar la contraseña para acceder a su cuenta de usuario
   */
  contrasenia: string;

  /**
   * Variable para almacenar la contraseña repetida para comprobar que la contraseña que ha introducido es la deseada
   */
  contraseniaRep: string;

  /**
   * Variable para almacenar la foto de perfil del usuario que esta creando
   */
  foto: string;

  /**
   * Variable para controlar que la petición de las contraseña coincidan
   */
  contraseniaCoincide: boolean = true;

  /**
  *  
  * Injectamos los servicios para la conexión con el Backend y el routing con las demás páginas
  * @param registSvc Servicio para hacer la consulta al backend
  * @param router Para el envio del Usuario a otras páginas
  */
  constructor(private registSvc: RegistroService, private router: Router) {}

  /**
   * Método que se varga cuando se inicializa la página
   */
  ngOnInit(): void {}

  /**
   * Método para pasar el archivo a Base 64 y poder guardarlo en la base de datos como un string
   * @param event recibimos el archivo de la foto
   */
  onFileSelected(event) {
    const file = event.target.files[0];

    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.foto = reader.result as string;
      };
    }
  }

  /**
   * Método que se carga cuando el usuario pulsa el botón "Registrarme"
   *
   * Recogemos los valores que el usuario introduce en el formulario de registro y los asignamos al objeto usuario
   *
   * Llamamos al servicio de Registro para la conexión con el back y mostramos por la consola
   * "Se ha guardado el usuario: " si el usuario se ha guardado correctamente o
   * "Error de red o error en el servidor" si el usuario no se ha guardado correctamente dependiendo de donde proceda el error
   */

  addUserService() {
    let usuario = new Usuarios(
      this.nick,
      this.contrasenia,
      this.nombre,
      this.apellido,
      this.email,
      this.foto
    );

    this.registSvc
      .addNewUser(usuario)
      .subscribe((response: UsuariosResponse) => {
        if (response.exito) {
          alert('Usuario registrado con éxito');
          this.router.navigate(['/login']);
        } else {
          alert('Este correo electrónico ya está en uso. Inserte uno válido.');
        }
      });
  }
}
