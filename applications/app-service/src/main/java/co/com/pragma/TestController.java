package co.com.pragma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public Mono<String> hello() {
        return Mono.just("Aplicación funcionando!");
    }

    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/create-user")
    public Mono<User> createUser() {
        User user = new User("Juan", "juan@test.com");
        return userRepository.save(user);
    }
}