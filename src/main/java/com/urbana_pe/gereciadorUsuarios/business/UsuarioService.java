package com.urbana_pe.gereciadorUsuarios.business;

import com.urbana_pe.gereciadorUsuarios.business.converter.UsuarioConverter;
import com.urbana_pe.gereciadorUsuarios.business.dtos.UsuarioDTO;
import com.urbana_pe.gereciadorUsuarios.infrastructure.entity.Usuario;
import com.urbana_pe.gereciadorUsuarios.infrastructure.exceptions.ResourceNotFoundException;
import com.urbana_pe.gereciadorUsuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

   private final UsuarioRepository usuarioRepository;
   private final UsuarioConverter usuarioConverter;


   public UsuarioService(UsuarioRepository usuarioRepository, UsuarioConverter usuarioConverter) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioConverter = usuarioConverter;
   }

    public List<UsuarioDTO> consultarUsuarios(){
       List<Usuario> usuarios = usuarioRepository.findAll();
       return usuarios.stream().map(usuarioConverter::paraUsuarioDTO).toList();

   }

   public UsuarioDTO incluirUsuario(UsuarioDTO dto) {
       Usuario entity = usuarioConverter.paraUsuarioEntity(dto);
       Usuario usuarioSalvo = usuarioRepository.save(entity);
       return usuarioConverter.paraUsuarioDTO(usuarioSalvo);
    }

    public UsuarioDTO alterarUsuario(Long id, UsuarioDTO dto){
       Usuario usuarioAlterado = usuarioRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("Id não encontrado!"));

       usuarioAlterado.setNome(dto.getNome() != null ? dto.getNome() :  usuarioAlterado.getNome());
       usuarioAlterado.setEmail(dto.getEmail() != null ? dto.getEmail() : usuarioAlterado.getEmail());
       Usuario usuarioSalvo = usuarioRepository.save(usuarioAlterado);
        return usuarioConverter.paraUsuarioDTO(usuarioSalvo);

    }

    public UsuarioDTO autenticar(String email, String senha) {
        if (email == null || senha == null) {
            throw new RuntimeException("Email ou senha não enviados pelo front");
        }
        List<Usuario> usuarios = usuarioRepository.findByEmail(email.trim());
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado: " + email);
        }
        Usuario usuario = usuarios.get(0);

        if (usuario.getSenha() == null) {
            throw new RuntimeException("Senha inválida. Tente cadastrar novamente.");
        }
        if (!usuario.getSenha().trim().equals(senha.trim())) {
            throw new RuntimeException("Senha inválida");
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(null); // não retorna senha por segurança
        return dto;
    }

    public void removerUsuario(Long id){
       usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarPorEmail(String email) {
       return usuarioRepository.findByEmail(email);
   }

}
