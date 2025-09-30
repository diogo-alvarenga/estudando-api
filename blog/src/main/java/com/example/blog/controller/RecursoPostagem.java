package com.example.blog.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.blog.entity.Postagem;
import com.example.blog.service.ServicoPostagem;

@RestController
@RequestMapping(value="/postagem")
public class RecursoPostagem {

	@Autowired
	private ServicoPostagem servico;
	
	
	@RequestMapping(method=RequestMethod.POST)//a mesma coisa que @PostMapping("/postagem")
	public ResponseEntity<Void> salvar(@RequestBody Postagem obj){
		obj = servico.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
