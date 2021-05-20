package com.mercadojob.entity;

public class anuncio {
    private int id;
    private categoria cat_id;
    private usuario user_id;
    private localidade loc_id;
    private double valor;
    private String nome,descCurta,descLonga,foto1,foto2,foto3;
	public anuncio(int id, categoria cat_id, usuario user_id, localidade loc_id, double valor, String nome,
			String descCurta, String descLonga, String foto1, String foto2, String foto3) {
		this.id = id;
		this.cat_id = cat_id;
		this.user_id = user_id;
		this.loc_id = loc_id;
		this.valor = valor;
		this.nome = nome;
		this.descCurta = descCurta;
		this.descLonga = descLonga;
		this.foto1 = foto1;
		this.foto2 = foto2;
		this.foto3 = foto3;
	}
	public anuncio() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public categoria getCat_id() {
		return cat_id;
	}
	public void setCat_id(categoria cat_id) {
		this.cat_id = cat_id;
	}
	public usuario getUser_id() {
		return user_id;
	}
	public void setUser_id(usuario user_id) {
		this.user_id = user_id;
	}
	public localidade getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(localidade loc_id) {
		this.loc_id = loc_id;
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