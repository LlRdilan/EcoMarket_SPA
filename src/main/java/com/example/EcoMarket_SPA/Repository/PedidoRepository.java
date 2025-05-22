package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {
   private List<Pedido> pedidos = new ArrayList<>();

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Pedido getPedido(int id){
        for(Pedido pe: pedidos){
            if(pe.getId()==id){
                return pe;
            }
        }
        return null;
    }

    public Pedido createPedido(Pedido pe){
        pedidos.add(pe);
        return pe;
    }
    public boolean deletePedido(int id){
        Pedido pe = this.getPedido(id);
        if(pe==null){
            return false;
        }else{
            pedidos.remove(pe);
            return true;
        }
    }
    public Pedido updatePedido(Pedido pe) {
        for(Pedido pe2 : pedidos) {
            if (pe.getId() == pe2.getId()) {
                pe2.setCliente(pe.getCliente());
                pe2.setProductos(pe.getProductos());
                pe2.setTotal(pe.getTotal());
                pe2.setEstadoPedido(pe.getEstadoPedido());
                return pe2;
            }
        }
        return null;
    }
}
