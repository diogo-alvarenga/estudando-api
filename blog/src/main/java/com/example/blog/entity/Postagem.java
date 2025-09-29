package com.example.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Postagem implements Serializable{

	private static final Long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String texto;
	
	@ManyToOne//relacionamento de muitos pra um
	private Autor autor;//objeto de conexão com a classe autor

	@OneToMany(mappedBy = "postagem")//relacionamento um para muitos
	private List<Comentario> comentarios = new ArrayList<Comentario>();
}
