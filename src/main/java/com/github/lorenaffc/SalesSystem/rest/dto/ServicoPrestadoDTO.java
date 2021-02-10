package com.github.lorenaffc.SalesSystem.rest.dto;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class ServicoPrestadoDTO {
	
	private String descricao;
	private String preco;
	private LocalDate dataCadastro;
	private Integer  idCliente;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void getDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	
	@Autowired
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	
	
}
