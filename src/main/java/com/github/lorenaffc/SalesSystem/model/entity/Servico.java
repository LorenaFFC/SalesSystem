package com.github.lorenaffc.SalesSystem.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 255)
	private String descricao;

	@Column(nullable = false)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Column(name = "data_cadastro", updatable=false)
	@JsonFormat(pattern ="dd/MM/yyyy")
	private LocalDate dataCadastro;

	
	// METODO QUE VAI SER EXECUTADO ANTES E INSERIR A DATA ATUAL EM DT CADASTRO
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
}
