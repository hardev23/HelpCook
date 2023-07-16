import { Component } from '@angular/core';



/**
 * Componente principal de la aplicación.
 *
 * Este componente representa el contenedor principal de la aplicación HelpCook.
 * Proporciona el menú principal de navegación y permite expandir/cerrar el menú lateral.
 *
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
   /**
   * Título de la aplicación.
   */
  title = 'HelpCook';

    /**
   * Indica si el menú lateral está abierto o cerrado.
   */
  opened = false;

   /**
   * Indica si el menú lateral está expandido o no.
   */
  expanded = false;

    /**
   * Crea una instancia del componente AppComponent.
   */
  constructor(){}
  
  /**
   * Alterna la visibilidad del menú lateral.
   */
  toggleSidenav() {
    this.expanded = !this.expanded;
  }
}
