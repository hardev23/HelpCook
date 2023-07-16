import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { Usuarios } from 'src/app/Usuarios.model';
import { RegistroService } from '../registro/registro-service.service';

/**
 * Componente para el acceso del Usuario a su cuenta
 * 
 * se comprobarán los datos y si coinciden se dará acceso a su perfil,
 * 
 * en caso contrario se muestra un mensaje de error
 */
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  /**
   * Variable para almacenar el email
   */
  email: string;

  /**
   * Variable para almacenar la contraseña
   */
  contrasenia: string;

  /**
   * Variable para almacenar el usuario que obtenemos de la base de datos
   */
  usuario:Usuarios;

  /**
   * Variable saber para la validación
   */
  validado:  boolean;
  
  /**
   * Injectamos los servicios 
   * @param router Para el envio del Usuario a otras páginas
   * @param registroService Servicio para hacer la consulta al backend
   */
  constructor(private router: Router, private registroService: RegistroService) {
    this.validado = false;
  }

  /**
   * Método que se ejecuta cuando se inicia la página
   */
  ngOnInit(): void {
   
  }
    
  /**
   * Método para registrar al usuario
   * 
   * le mandamos el email y la contraseña y si existe y coincide en el front
   * 
   * nos devuelve un Objeto del Usuario con todos sus datos que mandamos a la página de perfil
   */
  login() {
    this.registroService.login(this.email,this.contrasenia).subscribe((usuario) => {
      this.usuario = usuario;
      this.validado = false;
      this.router.navigate(['/perfil'],{state:{usuario:usuario}}); // Redireccionar a la página de perfil
    }, (error) => {
      console.log(error);
      this.validado = true;
      
    });
  }

}
