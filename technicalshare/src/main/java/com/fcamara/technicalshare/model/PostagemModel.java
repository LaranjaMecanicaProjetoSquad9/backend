package com.fcamara.technicalshare.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name="tb_postagem") 
public class PostagemModel {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY) 
	private Long id; 
	
	@NotNull 
	private String titulo;
	
	@NotNull 
	@Size(min=4, max=1000) 
	private String texto;
	
	private String foto;
	
	@Temporal(TemporalType.TIMESTAMP)  
	private Date date= new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private UsuarioModel usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}
}