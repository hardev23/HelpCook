package com.help.cook.helpcook.business;

import com.help.cook.helpcook.models.UsuariosResponse;
import com.help.cook.helpcook.repository.RecetasRepository;
import com.help.cook.helpcook.repository.domain.Recetas;
import com.help.cook.helpcook.repository.domain.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.help.cook.helpcook.models.ValoracionesRequest;
import com.help.cook.helpcook.models.ValoracionesResponse;
import com.help.cook.helpcook.repository.ValoracionesRepository;
import com.help.cook.helpcook.repository.domain.Valoraciones;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase con la lógica del Negocio Valoraciones
 * @author Jennifer
 * @version 1.0, 2022/11/05
 */
@Service
public class ValoracionesBusinessImpl implements IValoracionesBusiness {

    @Autowired
    private ValoracionesRepository valoracionesRepository;

    @Autowired
    private RecetasRepository recetasRepository;

    
    /**
     * Método con la lógica para crear una valoración,
     * asignamos a una nueva Valoración los datos dados por el usuario, la guardamos en el repositorio,
	 * llamamos a la función ValoraciónMedia para calcular la media con la nueva valoración,
	 * asignamos los valores al objeto a devolver 
     */
    @Override
    public ValoracionesResponse crear(ValoracionesRequest request) {

        Valoraciones valoraciones = new Valoraciones();
       

        ValoracionesResponse response = new ValoracionesResponse();
      
        
        valoraciones.setIdRecetas(request.getIdRecetas());
        valoraciones.setValor(request.getValor());

          
        
        Valoraciones datoGuardado = valoracionesRepository.save(valoraciones);

        
        valoracionMedia(datoGuardado.getIdRecetas());

        response.setIdValoraciones(datoGuardado.getIdValoraciones());
        response.setIdRecetas(datoGuardado.getIdRecetas());
        response.setValor(datoGuardado.getValor());


      

        return response;

    }

    /**
     * Método con la lógica para obtener una valoración,
     * recuperamos la valoracióin del repositorio mediante su id,
	 * asignamos al objeto a devolver los datos de la Valoración que hemos recuperado. 
     */
    @Override
    public ValoracionesResponse obtener(Integer id) {

        ValoracionesResponse response = new ValoracionesResponse();

        Valoraciones datoGuardado = valoracionesRepository.findById(id).get();

        response.setIdValoraciones(datoGuardado.getIdValoraciones());
        response.setIdRecetas(datoGuardado.getIdRecetas());
        response.setValor(datoGuardado.getValor());

    




        return response;
    }

    
    
    /**
 	* Método con la lógica para eliminar una valoración,
 	* recuperamos la receta a la que pertenece la valoración que se va a eliminar,
	* borramos la valoración,
	* llamamos a la función ValoraciónMedia para calcular la media de la receta después de haber borrado la valoración
	*/
    public void eliminar(Integer id) {
    	
    	Integer idReceta = valoracionesRepository.findById(id).get().getIdRecetas();
    	
        valoracionesRepository.deleteById(id);
        
        valoracionMedia(idReceta);   
        
    }

    
    
    /**
     * Método con la lógica para modificar una valoración,
     * recuperamos la valoración, le asignamos los nuevos valores y la guardamos en el repositorio,
	 * asignamos los nuevos valores al objeto a devolver,
	 * llamamos a la función ValoraciónMedia para calcular la media de la receta después de haber modificado la valoración
     */
    @Override
    public ValoracionesResponse modificar(ValoracionesRequest request, Integer id) {

        ValoracionesResponse response = new ValoracionesResponse();

        Valoraciones datoGuardado = valoracionesRepository.findById(id).get();

        datoGuardado.setIdRecetas(request.getIdRecetas());
        datoGuardado.setValor(request.getValor());

        Valoraciones datoModificado = valoracionesRepository.save(datoGuardado);
        
        

        response.setIdValoraciones(datoModificado.getIdValoraciones());
        response.setIdRecetas(datoModificado.getIdRecetas());
        response.setValor(datoModificado.getValor());

     
               
        valoracionMedia(datoGuardado.getIdRecetas());
           


 

        return response;
    }
    
/**
 * Método con la lógica para obtener todas las valoraciónes,
 * recuperamos la lista de valoraciones del repositorio,
 * accdemos a cada una de las valoraciones y asignamos sus valores a la valoración a devolver,
 * añadimos a la lista a devolver todas las valoraciones a devolver
 */
public List<ValoracionesResponse> obtenerTodos() {
		
		List<ValoracionesResponse> valoracionesResponseLista = new ArrayList<>();
		List<Valoraciones> valoracionesLista = valoracionesRepository.findAll();
		
		for(Valoraciones valoraciones: valoracionesLista) {
			
			ValoracionesResponse valoracionesResponse = new ValoracionesResponse();
			
			
			valoracionesResponse.setIdValoraciones(valoraciones.getIdValoraciones());
			valoracionesResponse.setIdRecetas(valoraciones.getIdRecetas());
			valoracionesResponse.setValor(valoraciones.getValor());
			
			
			valoracionesResponseLista.add(valoracionesResponse);	
			
		}
		
		return valoracionesResponseLista;
	}
    

   /**
    * Método para calcular la valoración media de una receta,
    * recuperamos la receta del repositorio, recuperamos en una lista todas las valoraciones de la receta,
    * sumamos todas las valoraciones que posee la receta y calculamos la media.
    * @param idRecetas. Recibimos el id de la receta donde realizar el calculo
    */
    public void valoracionMedia(Integer idRecetas) {
    	
    	 //PARA CALCULAR MEDIA DE LA VALORACIÓN DE LA RECETA
        
        Recetas recetaGuardada = recetasRepository.findById(idRecetas).get(); //Recuperamos la receta

        List<Valoraciones> valoracionesGuardadas = valoracionesRepository.findByIdRecetas(recetaGuardada.getIdRecetas()); // Recuperamos todas las valoraciones de una receta 
        int sumaMedia = 0; // Creamos una variable para almacenar todas la valoraciones
        for (Valoraciones valoracionMedia : valoracionesGuardadas) { //Recorremos la lista de todas las valoraciones que tenia la receta
            sumaMedia += valoracionMedia.getValor(); //Sumamos todas las valoraciones 
        }

        float valorMedio =  (float) sumaMedia / (valoracionesGuardadas.size()); // Calculamos la media recuperando la cantidad de valoraciones
        recetaGuardada.setValoracionMedia(valorMedio); //Asignamos el nuevo dato
        recetasRepository.save(recetaGuardada); //Guardamos el dato
    	
    }
   
}
