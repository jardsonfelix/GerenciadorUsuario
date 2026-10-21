package com.urbana_pe.gereciadorUsuarios.business;


import com.urbana_pe.gereciadorUsuarios.infrastructure.entity.Admin;
import com.urbana_pe.gereciadorUsuarios.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {


    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin salvar(Admin adm) {
        return adminRepository.save(adm);
    }

    public Optional<Admin> autenticar(String email, String senha) {
        return adminRepository.findByEmailAndSenha(email, senha);
    }
}