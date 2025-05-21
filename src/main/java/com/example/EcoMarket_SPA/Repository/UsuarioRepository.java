package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    private ArrayList<Usuario> usuarios  = new ArrayList<>();

    //Metodo que retornara todas las ventas
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    //Metodo que mostrara el libro segun su id
    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    //Metodo que guardara una venta
    public Usuario guardar(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    //Metodo que actualizara la venta
    public Usuario actualizar(Usuario usuario) {
        int id = 0;
        int idPosition = 0;

        for(int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getId() == usuario.getId()) {
                id = usuario.getId();
                idPosition = i;
            }
        }
        Usuario usuario1 = new Usuario();
        usuario1.setId(id);
        usuario1.setNombre(usuario.getNombre());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setRol(usuario.getRol());
        usuario1.setPassword(usuario.getPassword());

        usuarios.set(idPosition, usuario1);
        return usuario1;
    }

    //Metodo que eliminara por id
    public void eliminar(int id){
        Usuario usuario = buscarPorId(id);
        if (usuario != null){
            usuarios.remove(usuario);
        }
    }
}
