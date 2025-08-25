package gateway;

import co.com.pragma.model.Usuario;
import reactor.core.publisher.Mono;

public interface UsuarioRepository {
    Mono<Usuario> guardar(Usuario usuario);
    Mono<Usuario> buscarPorEmail(String email);
}