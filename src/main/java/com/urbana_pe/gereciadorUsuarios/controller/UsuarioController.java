package com.urbana_pe.gereciadorUsuarios.controller;

import com.urbana_pe.gereciadorUsuarios.infrastructure.entity.Usuario;
import com.urbana_pe.gereciadorUsuarios.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> consultarUsuarios(@RequestParam(value = "email", required = false)
                                                               String email) {
        if(email != null && !email.isEmpty()) {
            List<Usuario> usuarios = usuarioService.buscarPorEmail(email);
            return ResponseEntity.ok(usuarios);
        }

        List<Usuario> usuarios = usuarioService.consultarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> incluirUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.incluirUsuario(usuario));
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(@RequestParam Long id, @RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.alterarUsuario(id, usuario));
    }

    @DeleteMapping
    public ResponseEntity<Void> excluirUsuario(@RequestParam Long id){
        usuarioService.removerUsuario(id);
        return ResponseEntity.ok().build();
    }
}
