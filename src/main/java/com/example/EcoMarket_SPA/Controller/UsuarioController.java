package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Usuario;
import com.example.EcoMarket_SPA.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioServices.getUsuarios();
    }

    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioServices.saveUsuario(usuario);
    }

    @GetMapping({"{id}"})
    public Usuario buscarUsuario(@PathVariable int id) {
        return usuarioServices.getUsuarioId(id);
    }

    @PutMapping({"{id}"})
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        return usuarioServices.updateUsuario(usuario);
    }

    @DeleteMapping({"{id}"})
    public String eliminarUsuario(@PathVariable int id) {
        return usuarioServices.deleteUsuario(id);
    }
}
