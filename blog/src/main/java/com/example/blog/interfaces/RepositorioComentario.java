package com.example.blog.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.entity.Comentario;

@Repository
public interface RepositorioComentario extends JpaRepository<Comentario, Integer>{

}
