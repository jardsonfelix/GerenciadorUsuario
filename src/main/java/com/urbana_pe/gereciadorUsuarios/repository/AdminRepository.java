package com.urbana_pe.gereciadorUsuarios.repository;

import com.urbana_pe.gereciadorUsuarios.infrastructure.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmailAndSenha(String email, String senha);
}

