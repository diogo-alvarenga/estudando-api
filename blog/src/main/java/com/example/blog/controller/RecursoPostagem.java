package com.example.blog.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
		URI uri = 
				ServletUriComponentsBuilder.fromCurrentRequest()//pega a url que chegou
				.path("/{id}")//adiciona /{id} no final da url
				.buildAndExpand(obj.getId())//pega o id do objeto e coloca no lugar de {id}
				.toUri();//converte para uri
												
		return ResponseEntity.created(uri).build();//coloca status como created, coloca a uri no cabeçalho e retorna pra o client
	}
	
	@RequestMapping(value ="/{id}", method= RequestMethod.GET)
	public ResponseEntity<Postagem> buscar(@PathVariable Integer id){
		Postagem obj = servico.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarTodos(){
		List<Postagem> list = servico.buscarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Postagem obj, @PathVariable Integer id){
		obj.setId(id);
		obj = servico.editar(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletar(@PathVariable int id) {
	    String resultado = servico.deletar(id);

	    if (resultado.contains("não encontrada")) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
	    } else if (resultado.startsWith("Exceção") || resultado.startsWith("Erro")) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultado);
	    } else {
	        return ResponseEntity.ok(resultado); // http 200 com mensagem "Postagem deletada com sucesso"
	    }
	}
	
}
