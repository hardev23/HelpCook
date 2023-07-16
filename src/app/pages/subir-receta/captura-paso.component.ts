import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

/**
 * Componente para la captura de los datos al crear el paso
 */
@Component({
  selector: 'app-captura-paso',
  templateUrl: './captura-paso.component.html',
  styleUrls: ['./subir-receta.component.css']
})
export class CapturaPasoComponent {

  /**
   * Variable para contar recibir el número de pasos que se insertan
   */
  @Input() contadorPasos: number;

  
  /**
   * Evento para mandar a SubirReceta los datos de la descripción y la foto del paso
   */
  @Output() pasoAgregado = new EventEmitter<{ descripcion: string, foto: string }>();
  pasoForm = new FormGroup({
  descripcionPaso: new FormControl('')
  });

  /**
   * Variable para almacenar la foto
   */
  foto: string;


  /**
   * Método para la conversion del archivo de la foto a un base64,
   * una vez convertido lo almacenamos en nuestra variable foto
   * @param event. Recibe el archivo que captura del evento
   */
  onFileSelectedPasos(event) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.foto = reader.result as string;
    };
    reader.readAsDataURL(file);
  }

  /**
   * Método para la captura de los datos del formulario y poner las variables vacias para una nueva inserción
   */
  agregar() {
    const descripcion = this.pasoForm.value.descripcionPaso;
    this.pasoAgregado.emit({ descripcion, foto: this.foto });
    this.pasoForm.reset();
    this.foto = null;
  }
}

