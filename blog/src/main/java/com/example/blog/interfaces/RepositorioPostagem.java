package com.example.blog.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.entity.Postagem;

@Repository
public interface RepositorioPostagem extends JpaRepository<Postagem, Integer>{

}
