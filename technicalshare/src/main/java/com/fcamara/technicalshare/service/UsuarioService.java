package com.fcamara.technicalshare.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.fcamara.technicalshare.model.UsuarioLogin;
import com.fcamara.technicalshare.model.UsuarioModel;
import com.fcamara.technicalshare.repository.UsuarioRepository;

@Service
public class UsuarioService 
{

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioModel cadastrarUsuario(UsuarioModel usuario) {
		if (usuarioRepository.findByEmailContainingIgnoreCase(usuario.getEmail()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário ja existe!", null);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return usuarioRepository.save(usuario);
	}

	public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario) {
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {

			Optional<UsuarioModel> buscaUsuario = usuarioRepository.findByEmailContainingIgnoreCase(usuario.getEmail());

			if (buscaUsuario.isPresent()) {

				if (buscaUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			}

			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

			if (idade < 18)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);

			return Optional.of(usuarioRepository.save(usuario));

		} else {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);

		}

	}

	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> userLogin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<UsuarioModel> usuario = usuarioRepository.findByEmailContainingIgnoreCase(userLogin.get().getEmail());
		if (usuario.isPresent()) {
			if (encoder.matches(userLogin.get().getSenha(), usuario.get().getSenha())) {

				String auth = userLogin.get().getEmail() + ":" + userLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				userLogin.get().setId(usuario.get().getId());
				userLogin.get().setNome(usuario.get().getNome());
				userLogin.get().setSenha(usuario.get().getSenha());
				userLogin.get().setFoto(usuario.get().getFoto());
				userLogin.get().setToken(authHeader);
				return userLogin;
			}
		}

		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);

	}

}