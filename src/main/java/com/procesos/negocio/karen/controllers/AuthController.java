package com.procesos.negocio.karen.controllers;

import com.procesos.negocio.karen.models.Usuario;
import com.procesos.negocio.karen.services.UsuarioService;
import com.procesos.negocio.karen.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping(value = "auth/login")

    public ResponseEntity login(@RequestBody Usuario usuario)
    {
       return usuarioService.login(usuario.getCorreo(),usuario.getPassword());

    }


}
