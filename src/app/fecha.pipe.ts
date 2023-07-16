import { Pipe, PipeTransform } from '@angular/core';

/**
 * Clase para la adaptación del formato de la fecha para poder mostrar sólo fecha sin hora
 */
@Pipe({
  name: 'fecha'
})
export class FechaPipe implements PipeTransform {
  transform(value: Date): string { 
    return value.toLocaleDateString('es-ES', {year: 'numeric', month: 'long', day: 'numeric'});
  }
}