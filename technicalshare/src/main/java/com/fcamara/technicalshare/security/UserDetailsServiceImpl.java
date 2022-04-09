package com.fcamara.technicalshare.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.fcamara.technicalshare.model.UsuarioModel;
import com.fcamara.technicalshare.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Optional<UsuarioModel> usuario = usuarioRepository.findByEmailContainingIgnoreCase(userName);
		
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + "Este usuário não foi encontrado"));
		
		return usuario.map(UserDetailsImpl::new).get();
		
	}
}
