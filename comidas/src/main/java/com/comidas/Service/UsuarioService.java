package com.comidas.Service;
import java.util.Optional;
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
}


    

