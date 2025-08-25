package co.com.pragma.usecase;

import co.com.pragma.model.Usuario;
import co.com.pragma.model.gateway.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
public class RegistrarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Mono<Usuario> registrar(Usuario usuario) {
        // Criterio: Campos no nulos
        if (Objects.isNull(usuario.getNombres()) || usuario.getNombres().isBlank() ||
                Objects.isNull(usuario.getApellidos()) || usuario.getApellidos().isBlank() ||
                Objects.isNull(usuario.getCorreoElectronico()) || usuario.getCorreoElectronico().isBlank() ||
                Objects.isNull(usuario.getSalarioBase())) {
            return Mono.error(new IllegalArgumentException("Nombres, apellidos, correo y salario no pueden ser nulos o vacíos."));
        }

        // Criterio: Salario en rango (0 a 15,000,000)
        if (usuario.getSalarioBase().compareTo(BigDecimal.ZERO) < 0 ||
                usuario.getSalarioBase().compareTo(new BigDecimal("15000000")) > 0) {
            return Mono.error(new IllegalArgumentException("El salario base debe estar entre 0 y 15.000,000."));
        }

        // Criterio: Email único
        return usuarioRepository.buscarPorEmail(usuario.getCorreoElectronico())
                .flatMap(usuarioExistente -> {
                    // Si el usuario ya existe, flatMap emite un error.
                    return Mono.error(new IllegalArgumentException("El correo electrónico ya está registrado."));
                })
                .switchIfEmpty(
                        // Si buscarPorEmail() viene vacío (el usuario no existe),
                        // entonces procede a guardar.
                        usuarioRepository.guardar(usuario)
                );
    }
}