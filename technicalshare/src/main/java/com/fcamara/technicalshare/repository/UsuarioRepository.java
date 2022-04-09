package com.fcamara.technicalshare.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fcamara.technicalshare.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long >
{
	public Optional<UsuarioModel> findByEmailContainingIgnoreCase(String Email); //Pesquisa por e-mail

	public List<UsuarioModel> findAllByNomeContainingIgnoreCase(String nome); //Pesquisa a lista por nome
}