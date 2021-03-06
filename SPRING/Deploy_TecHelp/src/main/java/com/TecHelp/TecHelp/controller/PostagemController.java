package com.TecHelp.TecHelp.controller;

import java.util.List;

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

import com.TecHelp.TecHelp.model.Postagem;
import com.TecHelp.TecHelp.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/curso/{curso}")
	public ResponseEntity<List<Postagem>> GetByCurso (@PathVariable String curso){
		return ResponseEntity.ok(repository.findAllByCursoContainingIgnoreCase(curso));
	}
	
	@GetMapping("/nivel/{nivel}")
	public ResponseEntity<List<Postagem>> GetBynivel (@PathVariable String nivel){
		return ResponseEntity.ok(repository.findAllByNivelContainingIgnoreCase(nivel));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> Post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> Put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void Delete (@PathVariable long id) {
		repository.deleteById(id);
	}

}
