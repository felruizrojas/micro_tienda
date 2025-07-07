package com.micro2_tiendas.micro2_tiendas.client;

import com.micro2_tiendas.micro2_tiendas.model.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api/usuarios").build();
    }

    public Mono<Usuario> obtenerUsuarioPorId(String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Usuario.class);
    }
}
