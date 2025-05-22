package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Usuario;
import com.example.EcoMarket_SPA.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioServices.getUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable int id) {
        return usuarioServices.getUsuarioId(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioServices.saveUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        return usuarioServices.updateUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        boolean eliminado = usuarioServices.deleteUsuario(id);
        return eliminado ? "Usuario eliminado" : "Usuario no encontrado";
    }
}
