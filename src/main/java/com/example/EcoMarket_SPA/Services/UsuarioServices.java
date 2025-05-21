package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Usuario;
import com.example.EcoMarket_SPA.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios(){
        return usuarioRepository.getUsuarios();
    }

    public Usuario saveUsuario (Usuario usuario) {
        return usuarioRepository.guardar(usuario);
    }

    public Usuario getUsuarioId(int id) {
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepository.actualizar(usuario);
    }

    public String  deleteUsuario(int id) {
        usuarioRepository.eliminar(id);
        return "Venta Eliminado";
    }
}
