package com.urbana_pe.gereciadorUsuarios.controller;

import com.urbana_pe.gereciadorUsuarios.business.AdminService;
import com.urbana_pe.gereciadorUsuarios.infrastructure.entity.Admin;
import com.urbana_pe.gereciadorUsuarios.repository.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public Admin criar(@RequestBody Admin adm) {
        return adminService.salvar(adm);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin login) {
        var admin = adminService.autenticar(login.getEmail(), login.getSenha());
        if (admin.isPresent()) return ResponseEntity.ok(admin.get());
        else return ResponseEntity.status(401).body("Login inválido");
    }
}
