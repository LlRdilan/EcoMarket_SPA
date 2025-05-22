package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Inventario;
import com.example.EcoMarket_SPA.Services.InventarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/inventario")
public class InventarioController {
    @Autowired
    private InventarioServices inventarioServices;

    @GetMapping
    public ResponseEntity<List<Inventario>> getAllInventario() {
        List<Inventario> inventarios = inventarioServices.getInventario();
        return new ResponseEntity<>(inventarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable int id) {
        Inventario inventario = inventarioServices.getInventario(id);
        if (inventario != null) {
            return new ResponseEntity<>(inventario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Inventario> addInventario(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = inventarioServices.saveInventario(inventario);
        return new ResponseEntity<>(nuevoInventario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> updateInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        Inventario actualizado = inventarioServices.updateInventario(inventario);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable int id) {
        inventarioServices.deleteInventario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
