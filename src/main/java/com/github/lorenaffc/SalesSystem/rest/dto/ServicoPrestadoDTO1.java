package com.github.lorenaffc.SalesSystem.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class ServicoPrestadoDTO1 {
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@NotEmpty(message = "{campo.preco.obrigatorio}")
	private String preco;
	
	@NotNull(message = "{campo.cliente.obrigatorio}")
	private Integer idCliente;
	
	@NotEmpty(message = "{campo.dataCadastro.obrigatorio}")
	private String data;
	
	

}


