package com.micro2_tiendas.micro2_tiendas.service;

import com.micro2_tiendas.micro2_tiendas.model.PersonalAsignado;
import com.micro2_tiendas.micro2_tiendas.repository.PersonalAsignadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonalAsignadoService {

    @Autowired
    private PersonalAsignadoRepository personalAsignadoRepository;

    // Obtener todas las asignaciones activas
    public List<PersonalAsignado> obtenerTodosActivos() {
        return personalAsignadoRepository.findAll()
                .stream()
                .filter(PersonalAsignado::isAsignacionActiva)
                .collect(Collectors.toList());
    }

    // Obtener por ID solo si está activo
    public Optional<PersonalAsignado> obtenerPorId(int id) {
        Optional<PersonalAsignado> personal = personalAsignadoRepository.findById(id);
        if (personal.isPresent() && personal.get().isAsignacionActiva()) {
            return personal;
        }
        return Optional.empty();
    }

    // Obtener asignaciones activas por tienda
    public List<PersonalAsignado> obtenerPorTiendaActiva(int idTienda) {
        return personalAsignadoRepository.findByTiendaIdTienda(idTienda)
                .stream()
                .filter(PersonalAsignado::isAsignacionActiva)
                .collect(Collectors.toList());
    }

    // Asignar personal (crea o actualiza)
    public PersonalAsignado asignarPersonal(PersonalAsignado personal) {
        personal.setAsignacionActiva(true); // Siempre activo al crear o reactivar
        return personalAsignadoRepository.save(personal);
    }

    // Desactivar asignación lógica
    public void desactivarAsignacion(int id) {
        Optional<PersonalAsignado> personal = personalAsignadoRepository.findById(id);
        personal.ifPresent(p -> {
            p.setAsignacionActiva(false);
            personalAsignadoRepository.save(p);
        });
    }
}
