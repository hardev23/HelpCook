package com.help.cook.helpcook.models;

import java.util.List;

import com.help.cook.helpcook.repository.domain.Pasos;

import lombok.Data;

/**
 * Objeto para la devolución del objeto Pasos
 * 
 * @author Hugo
 * @version 1.0, 2022/11/05
 *
 */
@Data
public class PasosResponse {

	private Integer idPasos;

	private Integer idRecetas;

	private Integer tipo;

	private String descripcion;

	private String foto;

	private List<PasosResponse> pasosResponseLista;

	private List<Pasos> pasosLista;
	

}
