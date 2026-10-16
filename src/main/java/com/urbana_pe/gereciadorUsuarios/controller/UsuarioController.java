package com.urbana_pe.gereciadorUsuarios.controller;

import com.urbana_pe.gereciadorUsuarios.business.dtos.UsuarioDTO;
import com.urbana_pe.gereciadorUsuarios.infrastructure.entity.Usuario;
import com.urbana_pe.gereciadorUsuarios.business.UsuarioService;
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
    public ResponseEntity<List<UsuarioDTO>> consultarUsuarios(@RequestParam(value = "email", required = false)
                                                               String email) {
        if(email != null && !email.isEmpty()) {
            return ResponseEntity.ok(usuarioService.consultarUsuarios());
        }
        return ResponseEntity.ok(usuarioService.consultarUsuarios());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> incluirUsuario(@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO novoUsuario = usuarioService.incluirUsuario(usuarioDTO);
        return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestParam Long id, @RequestBody UsuarioDTO dto){
        return ResponseEntity.ok(usuarioService.alterarUsuario(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> excluirUsuario(@RequestParam Long id){
        usuarioService.removerUsuario(id);
        return ResponseEntity.ok().build();
    }
}
