package com.help.cook.helpcook.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.help.cook.helpcook.models.IngredientesResponse;
import com.help.cook.helpcook.models.PasosResponse;
import com.help.cook.helpcook.models.RecetasIngredientesRequest;
import com.help.cook.helpcook.models.RecetasPasosRequest;
import com.help.cook.helpcook.models.RecetasRequest;
import com.help.cook.helpcook.models.RecetasResponse;
import com.help.cook.helpcook.repository.PasosRepository;
import com.help.cook.helpcook.repository.RecetasIngredientesRepository;
import com.help.cook.helpcook.repository.RecetasRepository;
import com.help.cook.helpcook.repository.domain.Ingredientes;
import com.help.cook.helpcook.repository.domain.Pasos;
import com.help.cook.helpcook.repository.domain.Recetas;
import com.help.cook.helpcook.repository.domain.RecetasIngredientes;


/**
 * Clase con la lógica del Negocio Recetas
 * @author Jennifer
 * @version 1.0, 2022/11/05
 * @see com.help.cook.helpcook.business
 */
@Service
public class RecetasBusinessImpl implements IRecetasBusiness {

    @Autowired
    private RecetasRepository recetasRepository;

    @Autowired
    private RecetasIngredientesRepository recetasIngredientesRepository;

    @Autowired
    private PasosRepository pasosRepository;

    /**
	 * Método con la lógica para crear una Receta,
	 * Asignamos los valores a una nueva receta, la guardamos en el repositorio,
	 * recorremos la lista de Ingredientes del objeto que nos llega del front, accedemos al id de los ingredientes 
	 * y el id de la receta y los asignamos a un objeto intermedio para establecer su relación,
	 * guardamos en el repositorio este objetoIntermedio,
	 * recorremos la lista de pasos que nos llega del Front y almacenamos los Pasos,
	 * le asignamos al objeto intermedio el id de la receta que recuperamos del repositorio para asignarle los pasos,
	 * asignamos todos estos valores al Paso a devolver y lo guardamos en el repositorio,
	 * asignamos al objeto Receta a devolver todos los valores obtenidos ya guardados en el repositorio
	 * 
	 */
    @Override
    public RecetasResponse crear(RecetasRequest request) {
        Recetas recetas = new Recetas();
        RecetasResponse response = new RecetasResponse();


        recetas.setDescripcion(request.getDescripcion());
        recetas.setTiempo(request.getTiempo());
        recetas.setFoto(request.getFoto());
        recetas.setTitulo(request.getTitulo());
        recetas.setIdUsuarios(request.getIdUsuarios());
        recetas.setCategoria(request.getCategoria());
        recetas.setValoracionMedia(0F);
        recetas.setFechaAlta(Timestamp.valueOf(request.getFechaAlta()));
        recetas.setComensales(request.getComensales());

        Recetas datoGuardado = recetasRepository.save(recetas); // Guardamos la receta

        for (RecetasIngredientesRequest recetasIngredientesRequest : request.getIngredientes()) { //Recorremos el objeto de ingrediente q nos envia el front

            Ingredientes ingredienteIntermedio = new Ingredientes(); //Creamos este objeto que es para la relación
            Recetas recetasIntermedio = new Recetas(); //Creamos este objeto que es para la relación

            RecetasIngredientes recetasIngredientes = new RecetasIngredientes(); //Creamos el objeto que guardaremos en repositorio con los datos obtenidos de los objetos intermedios


            ingredienteIntermedio.setIdIngredientes(recetasIngredientesRequest.getIdIngredientes()); //Asignamos el id del ingredinete que llega del front al objeto ingrediente intermedio
            recetasIntermedio.setIdRecetas(datoGuardado.getIdRecetas()); //Asignamos el id de la receta que acabamos de crear al objeto de receta intermedio

            recetasIngredientes.setIngredientes(ingredienteIntermedio); //Asignamos al objeto ue vamos a guardar los valores que acabamos de conseguir
            recetasIngredientes.setRecetas(recetasIntermedio);
            recetasIngredientes.setCantidad(recetasIngredientesRequest.getCantidad());


            recetasIngredientesRepository.save(recetasIngredientes); //Guardamos en el repositorio
        }


        for (RecetasPasosRequest pasosRequest : request.getPasos()) { //Recorremos el objeto que nos llega del front

            Pasos recetaPaso = new Pasos(); //Creamos el objeto para almacenar los datos
            Recetas recetasIntermedio = new Recetas(); //Creamos este objeto intermedio para almacenar los datos de la receta que acabamos de crear

            recetasIntermedio.setIdRecetas(datoGuardado.getIdRecetas()); //Asignamos a este objeto el id de la receta 

            recetaPaso.setRecetas(recetasIntermedio); //Asignamos los valores de los atributos de éste objeto
            recetaPaso.setTipo(pasosRequest.getTipo());
            recetaPaso.setDescripcion(pasosRequest.getDescripcion());
            recetaPaso.setFoto(pasosRequest.getFoto());

            pasosRepository.save(recetaPaso);//Lo guardamos en el repositorio

        }


        response.setIdRecetas(datoGuardado.getIdRecetas());
        response.setIdUsuarios(datoGuardado.getIdUsuarios());
        response.setDescripcion(datoGuardado.getDescripcion());
        response.setTiempo(datoGuardado.getTiempo());
        response.setFoto(datoGuardado.getFoto());
        response.setTitulo(datoGuardado.getTitulo());
        response.setCategoria(datoGuardado.getCategoria());
        response.setFechaAlta(datoGuardado.getFechaAlta().toLocalDateTime());
        response.setValoracionMedia(datoGuardado.getValoracionMedia());
        response.setComensales(datoGuardado.getComensales());


        return response;

    }

    /**
	 * Método con la lógica para recuperar una Receta,
	 * recuperamos la receta del repositorio mediante su id,
	 * Asignamos los valores recuperados a la receta a devolver
	 */
    @Override
    public RecetasResponse obtener(Integer id) {

        RecetasResponse response = new RecetasResponse();

        List<IngredientesResponse> ingredientesResponseList = new ArrayList<>();
        List<PasosResponse> pasosResponseList = new ArrayList<>();


        Recetas datoGuardado = recetasRepository.findById(id).get();

        response.setIdRecetas(datoGuardado.getIdRecetas());
        response.setDescripcion(datoGuardado.getDescripcion());
        response.setTiempo(datoGuardado.getTiempo());
        response.setFoto(datoGuardado.getFoto());
        response.setTitulo(datoGuardado.getTitulo());
        response.setCategoria(datoGuardado.getCategoria());
        response.setFechaAlta(datoGuardado.getFechaAlta().toLocalDateTime());
        response.setValoracionMedia(datoGuardado.getValoracionMedia());
        response.setComensales(datoGuardado.getComensales());

        for (RecetasIngredientes ingredientesRecetas : datoGuardado.getIngredientes()) {
            IngredientesResponse ingredientesResponse = new IngredientesResponse();
            ingredientesResponse.setIdIngredientes(ingredientesRecetas.getIngredientes().getIdIngredientes());
            ingredientesResponse.setTipo(ingredientesRecetas.getIngredientes().getTipo());
            ingredientesResponse.setNombre(ingredientesRecetas.getIngredientes().getNombre());
            ingredientesResponse.setCantidad(ingredientesRecetas.getCantidad());
            ingredientesResponseList.add(ingredientesResponse);
        }

        for (Pasos pasos : datoGuardado.getPasos()) {

            PasosResponse pasosResponse = new PasosResponse();
            pasosResponse.setDescripcion(pasos.getDescripcion());
            pasosResponse.setTipo(pasos.getTipo());
            pasosResponse.setFoto(pasos.getFoto());


            pasosResponseList.add(pasosResponse);

        }

        response.setIngredientesResponse(ingredientesResponseList);
        response.setPasosResponse(pasosResponseList);

        return response;

    }
    
  
    /**
	 * Método con la lógica para eliminar una Receta,
	 * borramos del repositorio la receta mediante su id
	 */
    public void eliminar(Integer id) {
        recetasRepository.deleteById(id);
    }

    
    /**
     * Método con la lógica para modificar una receta,
     * recuperamos la receta del repositorio mediante su id,
     * le asignamos los nuevois valores y la guardamos en el repositorio,
     * asignamos al objeto a devolver los nuevos valores
     */
    @Override
    public RecetasResponse modificar(RecetasRequest request, Integer id) {

        RecetasResponse response = new RecetasResponse();

        Recetas datoGuardado = recetasRepository.findById(id).get();

        datoGuardado.setDescripcion(request.getDescripcion());
        datoGuardado.setTiempo(request.getTiempo());
        datoGuardado.setFoto(request.getFoto());
        datoGuardado.setTitulo(request.getTitulo());
        datoGuardado.setCategoria(request.getCategoria());
        datoGuardado.setFechaAlta(Timestamp.valueOf(request.getFechaAlta()));
        datoGuardado.setValoracionMedia(request.getValoracionMedia());
        datoGuardado.setComensales(request.getComensales());

        Recetas datoModificado = recetasRepository.save(datoGuardado);

        response.setIdRecetas(datoModificado.getIdRecetas());
        response.setDescripcion(datoModificado.getDescripcion());
        response.setTiempo(datoModificado.getTiempo());
        response.setFoto(datoModificado.getFoto());
        response.setTitulo(datoModificado.getTitulo());
        response.setCategoria(datoModificado.getCategoria());
        response.setFechaAlta(datoModificado.getFechaAlta().toLocalDateTime());
        response.setValoracionMedia(datoModificado.getValoracionMedia());
        response.setComensales(datoModificado.getComensales());

        return response;
    }

    
    /**
	 * Método con la lógica para recuperar una lista de recetas, según el filtro que se le envie:
	 * Todas las recetas, según su categoria, según los ingredientes que posea, según el Usuario que la haya subido, 
	 * según el parámetro de ordenación o más recientes o mejor valoradas.
	 * si no se recibe ningún parámetro devolverá todas las recetas,
	 * si se recibe la categoria mostrará sólo las recetas de esa categoria,
	 * si se recibe una lista de ingredientes sólo mostrará las recetas que contengan esos ingredientes,
	 * si se recibe el IdUsuario, mostrará las rfecetas que haya subido ese Usuario,
	 * si se recibe el parámetro ordenación se mostrarán las recetas ordenadas según el parámetro recibido
	 * 
	 */
    @Override
    public List<RecetasResponse> obtenerTodos(String categoria, List<Integer> idIngredientes, Integer idUsuario, String ordenacion) {
    	
    	
    	
    	 List<RecetasResponse> recetasResponseLista = new ArrayList();
    	 
        if(ordenacion==null){	
        Set<Recetas> recetasLista = recetasRepository.findAdvance(categoria, idIngredientes, idUsuario);
        recetasResponseLista= recuperarReceta(recetasLista);
       
        }else if(ordenacion.equals("Recetas mejor valoradas")) {
        	Set<Recetas> recetasLista = recetasRepository.findByValoradas();
        	 recetasResponseLista= recuperarReceta(recetasLista);
        	
        	
        }else if(ordenacion.equals("Recetas más recientes")) {
        	Set<Recetas> recetasLista = recetasRepository.findByFechaAlta();
        	 recetasResponseLista= recuperarReceta(recetasLista);
            
        }    

        return recetasResponseLista;
    }
    
    
    /**
     * Método para obtener las recetas mediante los favoritos
     * Le mandamos una lista con los id de las Recetas que tiene un usuario cómo favoritos
     * llamamos al repositorio para obtener la lista de las recetas de los favoritos,
     * y llamamos al método recuperarReceta para almacenarlas en la lista que vamos a devolver 
     */
    public List<RecetasResponse> obtenerFavoritos (List<Integer> idRecetas){
    	
    	List<RecetasResponse> recetasResponseLista = new ArrayList();
    	
    	Set<Recetas> recetasLista = recetasRepository.findByFavoritos(idRecetas);
    	
    	recetasResponseLista = recuperarReceta(recetasLista);
    	
    	return recetasResponseLista;
    	
    }
    
    /**
     * Método almacenar las recetas que vamos a devolver,
     * dependiendo de la lista que reciba del repositorio creará una lista a devolver dependiendo de los parámetros que se hayan envbiado como filtros,
     * recorremos la lista recuperada, recuperando todas las recetas y su lista de ingredientes y pasos y asignando éstos valores a la Lista a devolver
     * @param recetasLista. Le mandamos la lista que hemos recuperado del repositorio
     * @return Lista a devolver con las recetas pertinentes
     */
    public List<RecetasResponse> recuperarReceta(Set<Recetas> recetasLista ) {
    	
    	
    	 List<RecetasResponse> recetasResponseLista = new ArrayList();
    	 
    	 for (Recetas receta : recetasLista) {
    		 
             RecetasResponse recetasResponse = new RecetasResponse();
             
             List<IngredientesResponse> ingredientesResponseList = new ArrayList<>();
             List<PasosResponse> pasosResponseList = new ArrayList<>();

             recetasResponse.setIdRecetas(receta.getIdRecetas());
             recetasResponse.setIdUsuarios(receta.getIdUsuarios());
             recetasResponse.setDescripcion(receta.getDescripcion());
             recetasResponse.setTiempo(receta.getTiempo());
             recetasResponse.setFoto(receta.getFoto());
             recetasResponse.setTitulo(receta.getTitulo());
             recetasResponse.setCategoria(receta.getCategoria());
             recetasResponse.setFechaAlta(receta.getFechaAlta().toLocalDateTime());
             recetasResponse.setValoracionMedia(receta.getValoracionMedia());
             recetasResponse.setComensales(receta.getComensales());


             for (RecetasIngredientes ingrediente : receta.getIngredientes()) {
                 IngredientesResponse ingredientesResponse = new IngredientesResponse();
                 
                 ingredientesResponse.setIdIngredientes(ingrediente.getIngredientes().getIdIngredientes());
                 ingredientesResponse.setNombre(ingrediente.getIngredientes().getNombre());
                 ingredientesResponse.setTipo(ingrediente.getIngredientes().getTipo());
                 ingredientesResponse.setCantidad(ingrediente.getCantidad());
                 ingredientesResponseList.add(ingredientesResponse);
             }
             recetasResponse.setIngredientesResponse(ingredientesResponseList);
      
             for (Pasos paso : receta.getPasos()) {
                 PasosResponse pasosResponse = new PasosResponse();
                 
                 pasosResponse.setIdPasos(paso.getIdPasos());
                 pasosResponse.setIdRecetas(paso.getRecetas().getIdRecetas());
                 pasosResponse.setTipo(paso.getTipo());
                 pasosResponse.setDescripcion(paso.getDescripcion());
                 pasosResponse.setFoto(paso.getFoto());
                 pasosResponseList.add(pasosResponse);
             }
             
   
             
             recetasResponseLista.add(recetasResponse);
             
         }
    	 return recetasResponseLista;
    }
    
    
    
}
