package com.example.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//anotações do lombok
@AllArgsConstructor//pode criar um objeto com todos os argumentos
@NoArgsConstructor//pode receber um objeto sem nada
/*
 * SOBRE A ANOTAÇÂO ABAIXO
 * 
 *Ela instrui o Lombok a gerar o código dos métodos equals() 
 *e hashCode() usando apenas o campo especificado (o id) 
 *como base para comparação e cálculo.
 *
 *hashCode():Finalidade: Quando você armazena objetos em 
 *coleções baseadas em hash (como HashMap ou HashSet), o 
 *Java precisa de um número (o código hash) para identificar 
 *e localizar rapidamente onde o objeto deve ser guardado ou 
 *recuperado. 
 *O que faz: O método gerado calcula um valor hash 
 *baseado somente no valor do campo id.
 *
 *Equals(): O método gerado compara o objeto atual com outro 
 *objeto, e eles serão considerados iguais apenas se os seus 
 *respectivos campos id forem iguais. Todos os outros campos 
 *(nome, email, etc.) são ignorados nessa comparação.

 */
@EqualsAndHashCode(of="id")
@Setter
@Getter
@Entity
public class Postagem implements Serializable{

	private static final Long serialVersionUID = 1L;//estudar
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String texto;
	
	//chave estrangeira
	@ManyToOne//relacionamento de muitos pra um
	@JoinColumn(name = "autor_id", nullable = false)
	private Autor autor;//objeto de conexão com a classe autor

	@OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL)//relacionamento um para muitos
	private List<Comentario> comentarios;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}
