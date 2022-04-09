package com.fcamara.technicalshare.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_usuario")
public class UsuarioModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Campo nome obrigatório")
	@Size(min = 5, max = 100)
	private String nome;
	
	@NotNull(message = "Campo Função obrigatório")
	@Size(min = 1, max = 30)
	private String funcao;
	
	@NotNull(message = "Campo Função obrigatório")
	@Size(min = 1, max = 500)
	private String habilidade;
	
	@NotNull(message = "Campo email obrigatório")
	@Size(min = 10, max = 11)
	private String telefone;
	
	@NotNull(message = "Campo email obrigatório")
	@Size(min = 1, max = 50)
	@Email
	private String email;
	
	@NotNull(message = "Campo senha obrigatório")
	@Size(min = 6)
	private String senha;
	
	@NotNull(message = "Campo data de nascimento obrigatório")
	private LocalDate dataNascimento;

	private LocalDate dataCriacaoConta = LocalDate.now();
	
	private String foto;
	
	@OneToMany(mappedBy ="usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<PostagemModel> postagens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataCriacaoConta() {
		return dataCriacaoConta;
	}

	public void setDataCriacaoConta(LocalDate dataCriacaoConta) {
		this.dataCriacaoConta = dataCriacaoConta;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<PostagemModel> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<PostagemModel> postagens) {
		this.postagens = postagens;
	} 
}
