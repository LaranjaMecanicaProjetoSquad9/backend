package com.fcamara.technicalshare.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fcamara.technicalshare.model.UsuarioLogin;
import com.fcamara.technicalshare.model.UsuarioModel;
import com.fcamara.technicalshare.repository.UsuarioRepository;
import com.fcamara.technicalshare.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController 
{
	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private UsuarioService userService; 

	@GetMapping
	public ResponseEntity<List<UsuarioModel>> GetAll() 
	{
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> GetById(@PathVariable Long id) 
	{
		return userRepository.findById(id).map(rest -> ResponseEntity.ok(rest))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return userService.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}

	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> Post(@RequestBody UsuarioModel usuario) 
	{
		UsuarioModel usuarioResp = userService.cadastrarUsuario(usuario);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResp);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	 @PutMapping("/atualizar") 
	 public ResponseEntity<UsuarioModel> putUsuario(@Valid @RequestBody UsuarioModel usuario)
	    {		
			return userService.atualizarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		}
	
	@DeleteMapping("/{id}") //{Id da variável a ser deletada}
	public void delete(@PathVariable Long id) 
	{
		userRepository.deleteById(id);
	}
}
