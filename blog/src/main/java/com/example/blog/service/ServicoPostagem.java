package com.example.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Postagem;
import com.example.blog.interfaces.RepositorioPostagem;

@Service
public class ServicoPostagem {
	
	@Autowired
	private RepositorioPostagem repo;
	
	public List<Postagem> buscarTodos(){
		return repo.findAll();
	}
	
	public Postagem buscar(int id) {
		Optional<Postagem> obj = repo.findById(id);
		//retorna ou lança a exceção
		return obj.orElseThrow(() -> new ObjetoNaoEncontrado("Objeto não encontrado! Id: "+id+ ", Tipo: "+Postagem.class.getName()));
	}
	
	public Postagem salvar(Postagem obj) {
		/*
		 * o JPA verifica se o valor do id tem valor ou é
		 * null. Se for null, ele faz um comando de
		 * INSERT INTO no banco de dados
		 * se tiver valor, ele faz um comando de
		 * UPDATE
		 * */
		obj.setId(null);
		return repo.save(obj);
	}
	
	public void deletar(int id) {
		buscar(id);
		repo.deleteById(id);
	}
	
	public Postagem editar(Postagem obj) {
		/*o id é retornado para o metodo buscar,
		 * que pode retornar o obj completo,
		 * e assim ele cai dentro de newObj*/
		Postagem newObj = buscar(obj.getId());
		modfificar(newObj, obj);
		return repo.save(newObj);	
	}
	
	public void modfificar(Postagem newObj, Postagem obj) {
		newObj.setAutor(obj.getAutor());
		newObj.setComentarios(obj.getComentarios());
		newObj.setTexto(obj.getTexto());
		newObj.setTitulo(obj.getTitulo());
	}
}
