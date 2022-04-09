package com.fcamara.technicalshare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fcamara.technicalshare.model.UsuarioModel;
import com.fcamara.technicalshare.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "", allowedHeaders = "")
public class UsuarioController 
{
	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	// private UsuarioModelService userService; 

	@GetMapping
	public ResponseEntity<List<UsuarioModel>> GetAll() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> GetById(@PathVariable Long id) {
		return userRepository.findById(id).map(rest -> ResponseEntity.ok(rest))
				.orElse(ResponseEntity.notFound().build());
	}

//	@PostMapping("/logar")
//	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
//		return userService.logar(user).map(resp -> ResponseEntity.ok(resp))
//				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
//
//	}

	//@PostMapping("/cadastrar")
	//public ResponseEntity<UsuarioModel> Post(@RequestBody UsuarioModel UsuarioModel) {

//		UsuarioModel UsuarioModelResp = userService.cadastrarUsuarioModel(UsuarioModel);
//		try {
//			return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioModelResp);
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().build();
//		}
//
//	}

//	@PutMapping("/atualizar")
//	public ResponseEntity<UsuarioModel> Put(@RequestBody UsuarioModel UsuarioModel) {
//		Optional<UsuarioModel> UsuarioModelResp = userService.atualizarUsuarioModel(UsuarioModel);
//		try {
//			return ResponseEntity.ok(UsuarioModelResp.get());
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().build();
//		}
//	}
	

	@DeleteMapping("/{id}") //{Id da variável a ser deletada}
	public void delete(@PathVariable Long id) 
	{
		userRepository.deleteById(id);
	}

}
