package com.comidas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comidas.Model.UsuarioModel;
import com.comidas.Service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioserive;
    
    @PostMapping
    public ResponseEntity<?> postCrearUsuario(@RequestBody UsuarioModel usuario) {
        try {
            UsuarioModel nuevo = usuarioserive.crearUsuario(usuario, usuario.getRol());
            return ResponseEntity.status(201).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Error: "+ e.getMessage());
        }

    }
    @PostMapping("/login")
    public ResponseEntity<?> LoginUsuario(UsuarioModel usuarioingresacredenciales){
        try {
            UsuarioModel usuario = usuarioserive.login(
                usuarioingresacredenciales.getCorreo(), 
                usuarioingresacredenciales.getContraseña()
            );
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            }
            return ResponseEntity.status(401).body("Las credenciales son incorrectas");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error en el LOGIN");
        }
    }     
}
