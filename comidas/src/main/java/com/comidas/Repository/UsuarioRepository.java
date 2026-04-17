package com.comidas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidas.Model.UsuarioModel;


public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    Optional<UsuarioModel> findByEmail(String email);
    UsuarioModel FindByResetToken(String Token);
}
