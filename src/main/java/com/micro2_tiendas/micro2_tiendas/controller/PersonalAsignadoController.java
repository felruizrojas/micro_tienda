package com.micro2_tiendas.micro2_tiendas.controller;

import com.micro2_tiendas.micro2_tiendas.model.PersonalAsignado;
import com.micro2_tiendas.micro2_tiendas.service.PersonalAsignadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalAsignadoController {

    @Autowired
    private PersonalAsignadoService personalService;

    // Listar asignaciones activas
    @GetMapping
    public List<PersonalAsignado> listarPersonalActivo() {
        return personalService.obtenerTodosActivos();
    }

    // Obtener asignación activa por id
    @GetMapping("/{id}")
    public ResponseEntity<PersonalAsignado> obtenerPorId(@PathVariable int id) {
        return personalService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener asignaciones activas por tienda
    @GetMapping("/tienda/{idTienda}")
    public List<PersonalAsignado> obtenerPorTienda(@PathVariable int idTienda) {
        return personalService.obtenerPorTiendaActiva(idTienda);
    }

    // Asignar personal (crear o reactivar)
    @PostMapping
    public PersonalAsignado asignarPersonal(@RequestBody PersonalAsignado personal) {
        return personalService.asignarPersonal(personal);
    }

    // Desactivar asignación
    @PutMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable int id) {
        personalService.desactivarAsignacion(id);
        return ResponseEntity.noContent().build();
    }
}
