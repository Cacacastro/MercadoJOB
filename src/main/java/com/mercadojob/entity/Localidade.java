package com.mercadojob.entity;

public class Localidade{
	private int id;
	private String cidade,estado,UF;
	
	public Localidade() {
	}

	public Localidade(int id, String cidade, String estado, String uF) {
		this.id = id;
		this.cidade = cidade;
		this.estado = estado;
		UF = uF;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}
	
	
	
	
	
}