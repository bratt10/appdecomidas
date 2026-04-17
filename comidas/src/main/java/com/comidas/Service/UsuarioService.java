package com.comidas.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.comidas.Model.UsuarioModel;
import com.comidas.Model.UsuarioModel.Rol;
import com.comidas.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuariorepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UsuarioModel crearUsuario(UsuarioModel usuario, Rol rol){
        Optional<UsuarioModel> existe = usuariorepo.findByEmail(usuario.getCorreo());
        if (existe.isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");

        }
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(contraseñaEncriptada);
        usuario.setRol(rol);
        return usuariorepo.save(usuario);
    }

    public UsuarioModel login(String gmail, String contraseña){
        Optional<UsuarioModel>validar = usuariorepo.findByEmail(gmail);
        if (!validar.isPresent()) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        UsuarioModel usuario = validar.get();
        if (!passwordEncoder.matches(contraseña, usuario.getContraseña())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
        return usuario;
    }
    public void eliminUsuarioModel(Long id){
        usuariorepo.deleteById(id);
    }
    public void generarToken(String email) {
        Optional<UsuarioModel> usuarioOpt = usuariorepo.findByEmail(email);

        if (!usuarioOpt.isPresent()) {
            return;
        }

        UsuarioModel usuario = usuarioOpt.get();

        String token = UUID.randomUUID().toString();

        usuario.setResetToken(token);
        usuario.setResetTokenExp(LocalDateTime.now().plusMinutes(15));

        usuariorepo.save(usuario);
    }

    public void resetPassword(String token, String nuevaPassword) {
        UsuarioModel usuario = usuariorepo.FindByResetToken(token);

        if (usuario == null) {
            throw new IllegalArgumentException("Token inválido");
        }

        if (usuario.getResetTokenExp().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expirado");
        }

        usuario.setContraseña(passwordEncoder.encode(nuevaPassword));
        usuario.setResetToken(null);
        usuario.setResetTokenExp(null);

        usuariorepo.save(usuario);
    }
}


    

