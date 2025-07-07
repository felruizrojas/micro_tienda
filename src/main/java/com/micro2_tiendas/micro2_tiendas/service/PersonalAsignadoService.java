package com.micro2_tiendas.micro2_tiendas.service;

import com.micro2_tiendas.micro2_tiendas.client.UsuarioClient;
import com.micro2_tiendas.micro2_tiendas.model.PersonalAsignado;
import com.micro2_tiendas.micro2_tiendas.model.Usuario;
import com.micro2_tiendas.micro2_tiendas.repository.PersonalAsignadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonalAsignadoService {

    private final PersonalAsignadoRepository personalAsignadoRepository;
    private final UsuarioClient usuarioClient;

    public PersonalAsignadoService(PersonalAsignadoRepository personalAsignadoRepository,
                                  UsuarioClient usuarioClient) {
        this.personalAsignadoRepository = personalAsignadoRepository;
        this.usuarioClient = usuarioClient;
    }

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

    // Asignar personal (crea o actualiza) CON validación contra microservicio Usuarios
    public PersonalAsignado asignarPersonal(PersonalAsignado personal) {
        // Validar que el usuario existe y está activo en microservicio Usuarios
        Usuario usuario = usuarioClient.obtenerUsuarioPorId(personal.getUser()).block();

        if (usuario == null || !usuario.isUsuarioActivo()) {
            throw new RuntimeException("Usuario no existe o no está activo en el microservicio Usuarios.");
        }

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
