package com.fcamara.technicalshare.service;

import java.nio.charset.Charset;
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
	
	public Optional<UsuarioModel>cadastrarUsuario(UsuarioModel email) 
	
	{
		
		if(usuarioRepository.findByEmailContainingIgnoreCase(email.getEmail()).isPresent()) 
			
		{
			return Optional.empty();
		}
				
		email.setSenha(criptografarSenha(email.getSenha()));
		return Optional.of(usuarioRepository.save(email));
	}
	
	
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin)
	{
		Optional<UsuarioModel> usuario = usuarioRepository.findByEmailContainingIgnoreCase(usuarioLogin.get().getEmail());
		
		if(usuario.isPresent()) 
		{
			if(compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) 
			{
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setToken(geradorBasicToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin;	
			}
		}
		
		return Optional.empty();
	}
	
	private String criptografarSenha(String senha) 
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaDoBanco) 
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaDoBanco);
	}
	
	private String geradorBasicToken(String usuario, String senha) 
	{
		String token = usuario + ":" + senha;
		byte[] tokenBase64= Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}
	
	public Optional<UsuarioModel> atualizarUsuario (UsuarioModel email) 
	{
        if (usuarioRepository.findById(email.getId()).isPresent()) 
        {
            Optional<UsuarioModel> cadeUsuario = usuarioRepository.findByEmailContainingIgnoreCase(email.getEmail());
            if ((cadeUsuario.isPresent()) && (cadeUsuario.get().getId() != email.getId())) 
            	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário já existe", null);

        email.setSenha(criptografarSenha(email.getSenha()));
        return Optional.of(usuarioRepository.save(email));
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "O usuário não foi encontrado", null);
	}
}