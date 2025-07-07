package com.micro2_tiendas.micro2_tiendas.controller;

import com.micro2_tiendas.micro2_tiendas.model.Tienda;
import com.micro2_tiendas.micro2_tiendas.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    // Obtener todas las tiendas activas
    @GetMapping
    public List<Tienda> listarTiendas() {
        return tiendaService.obtenerTiendasActivas();
    }

    // Obtener tienda activa por id
    @GetMapping("/{id}")
    public ResponseEntity<Tienda> obtenerPorId(@PathVariable int id) {
        return tiendaService.obtenerTiendaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear tienda
    @PostMapping
    public Tienda crearTienda(@RequestBody Tienda tienda) {
        return tiendaService.crearTienda(tienda);
    }

    // Desactivar tienda (no eliminar físicamente)
    @PutMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivarTienda(@PathVariable int id) {
        tiendaService.desactivarTienda(id);
        return ResponseEntity.noContent().build();
    }
}
