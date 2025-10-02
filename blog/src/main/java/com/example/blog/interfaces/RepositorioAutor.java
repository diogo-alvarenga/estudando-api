package com.example.blog.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.entity.Autor;

@Repository
public interface RepositorioAutor extends JpaRepository<Autor, Integer>{

}
