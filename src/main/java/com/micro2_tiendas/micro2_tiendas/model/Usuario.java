package com.micro2_tiendas.micro2_tiendas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Usuario {
    private int id;
    private String user;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;

    private boolean usuarioActivo;

    private String run;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String correo;
    private String direccion;
    private String ciudad;
    private String region;

    @JsonProperty("estado")
    public String getEstado() {
        return usuarioActivo ? "activo" : "desactivo";
    }

    private Rol rol;

    @JsonProperty("rol")
    public Rol getRolVisible() {
        if (rol != null && rol.isRolActivo()) {
            return rol;
        }
        return null;
    }

    @Data
    public static class Rol {
        private int id;
        private String nombre;
        private boolean rolActivo;
    }
}
