package com.urbana_pe.gereciadorUsuarios.controller;

import com.urbana_pe.gereciadorUsuarios.business.dtos.UsuarioDTO;
import com.urbana_pe.gereciadorUsuarios.infrastructure.entity.Usuario;
import com.urbana_pe.gereciadorUsuarios.business.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
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
        System.out.println("CONTROLLER - CHEGOU DO ANGULAR: " + usuarioDTO.getEmail()
                + " SENHA: " + usuarioDTO.getSenha());
        UsuarioDTO novoUsuario = usuarioService.incluirUsuario(usuarioDTO);
        return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO dto){
        return ResponseEntity.ok(usuarioService.alterarUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id){
        usuarioService.removerUsuario(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String senha = body.get("senha");

        System.out.println("MAP - EMAIL: " + email + " SENHA: " + senha);

        UsuarioDTO usuarioLogado = usuarioService.autenticar(email, senha);

        return ResponseEntity.ok(usuarioLogado);
    }
}
