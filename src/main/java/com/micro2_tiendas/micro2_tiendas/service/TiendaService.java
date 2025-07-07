package com.micro2_tiendas.micro2_tiendas.service;

import com.micro2_tiendas.micro2_tiendas.model.Tienda;
import com.micro2_tiendas.micro2_tiendas.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    // Obtener todas las tiendas activas
    public List<Tienda> obtenerTiendasActivas() {
        return tiendaRepository.findByActivaTrue();
    }

    // Obtener tienda por id solo si está activa
    public Optional<Tienda> obtenerTiendaPorId(int id) {
        Optional<Tienda> tienda = tiendaRepository.findById(id);
        if (tienda.isPresent() && tienda.get().isActiva()) {
            return tienda;
        }
        return Optional.empty();
    }

    // Crear o actualizar tienda (si quieres actualizar usa el mismo método)
    public Tienda crearTienda(Tienda tienda) {
        tienda.setActiva(true); // siempre activa al crear
        return tiendaRepository.save(tienda);
    }

    // Desactivar tienda (en vez de eliminar físicamente)
    public void desactivarTienda(int id) {
        Optional<Tienda> tienda = tiendaRepository.findById(id);
        tienda.ifPresent(t -> {
            t.setActiva(false);
            tiendaRepository.save(t);
        });
    }
}
