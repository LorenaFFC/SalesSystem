package com.github.lorenaffc.SalesSystem.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	@NotEmpty(message ="{campo.nome.obrigatorio}")
	private String nome;

	@Column(nullable = false, length = 11)
	@NotNull(message = "{campo.cpf.obrigatorio}")
	@CPF(message = "{campo.CPF.invalido}")
	private String cpf;

	@Column(name = "data_cadastro", updatable=false)
	@JsonFormat(pattern ="dd/MM/yyyy")
	private LocalDate dataCadastro;

	
	// METODO QUE VAI SER EXECUTADO ANTES E INSERIR A DATA ATUAL EM DT CADASTRO
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
	
	

}
