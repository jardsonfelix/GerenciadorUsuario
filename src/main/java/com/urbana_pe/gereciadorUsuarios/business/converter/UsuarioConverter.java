package com.urbana_pe.gereciadorUsuarios.business.converter;

import com.urbana_pe.gereciadorUsuarios.business.dtos.UsuarioDTO;
import com.urbana_pe.gereciadorUsuarios.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    //converter de UsuarioDTO para UsuarioEntity

    public Usuario paraUsuarioEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        return usuario;

    }

    public List<Usuario> listarUsuariosEntity(List<UsuarioDTO>  dto) {
        List<Usuario> usuarios = new ArrayList<>();
        for (UsuarioDTO usuarioDTO : dto) {
            Usuario usuario = paraUsuarioEntity(usuarioDTO);
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public Usuario alterarUsuarioEntity(UsuarioDTO dto, Usuario entity) {
        Usuario usuario = paraUsuarioEntity(dto);
        usuario.setNome(dto.getNome() != null ? dto.getNome() : usuario.getNome());
        usuario.setEmail(dto.getEmail() != null ? dto.getEmail() : usuario.getEmail());
        return usuario;
    }

    //converter de usuarioEntity para usuarioDTO

    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());

        return usuarioDTO;

    }

    public List<UsuarioDTO> listarUsuariosDTO(List<Usuario> entity) {
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
        for (Usuario usuarios : entity) {
            UsuarioDTO usuarioDTO = paraUsuarioDTO(usuarios);
            usuarioDTOs.add(usuarioDTO);
        }
        return usuarioDTOs;
    }

    public UsuarioDTO alterarUsuario(Usuario entity, UsuarioDTO dto) {
        UsuarioDTO usuarioDTO = paraUsuarioDTO(entity);
        usuarioDTO.setNome(entity.getNome() != null ? entity.getNome() : usuarioDTO.getNome());
        usuarioDTO.setEmail(entity.getEmail() != null ? entity.getEmail() : usuarioDTO.getEmail());
        return usuarioDTO;
    }



}
