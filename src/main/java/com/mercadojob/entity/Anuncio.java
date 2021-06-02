package com.mercadojob.entity;

public class Anuncio {
    private int id;
    private Categoria categoria;
    private Usuario usuario;
    private Localidade localidade;
    private double valor;
    private String nome,descCurta,descLonga,foto1,foto2,foto3;
	public Anuncio(int id, Categoria categoria, Usuario usuario, Localidade localidade, String nome,
			String descCurta, double valor, String descLonga, String foto1, String foto2, String foto3) {
		this.id = id;
		this.categoria = categoria;
		this.usuario = usuario;
		this.localidade = localidade;
		this.valor = valor;
		this.nome = nome;
		this.descCurta = descCurta;
		this.descLonga = descLonga;
		this.foto1 = foto1;
		this.foto2 = foto2;
		this.foto3 = foto3;
	}
	public Anuncio() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescCurta() {
		return descCurta;
	}
	public void setDescCurta(String descCurta) {
		this.descCurta = descCurta;
	}
	public String getDescLonga() {
		return descLonga;
	}
	public void setDescLonga(String descLonga) {
		this.descLonga = descLonga;
	}
	public String getFoto1() {
		return foto1;
	}
	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}
	public String getFoto2() {
		return foto2;
	}
	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}
	public String getFoto3() {
		return foto3;
	}
	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}
    
    
}