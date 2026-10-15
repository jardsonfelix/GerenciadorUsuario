package com.urbana_pe.gereciadorUsuarios.service;

import com.urbana_pe.gereciadorUsuarios.infrastructure.entity.Usuario;
import com.urbana_pe.gereciadorUsuarios.infrastructure.exceptions.ResourceNotFoundException;
import com.urbana_pe.gereciadorUsuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

   private final UsuarioRepository usuarioRepository;


   public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
   }

    public List<Usuario> consultarUsuarios(){
       return usuarioRepository.findAll();

   }

   public Usuario incluirUsuario(Usuario usuario){
       return usuarioRepository.save(usuario);
    }

    public Usuario alterarUsuario(Long id, Usuario usuario){
       Usuario usuarioAlterado = usuarioRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("Id não encontrado!"));

       usuarioAlterado.setNome(usuario.getNome() != null ? usuario.getNome() :  usuarioAlterado.getNome());
       usuarioAlterado.setEmail(usuario.getEmail() != null ? usuario.getEmail() : usuarioAlterado.getEmail());
       usuarioAlterado.setSenha(usuario.getSenha() != null ? usuario.getSenha() : usuarioAlterado.getSenha());

        return usuarioRepository.save(usuarioAlterado);

    }

    public void removerUsuario(Long id){
       usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarPorEmail(String email) {
       return usuarioRepository.findByEmail(email);    }
}
